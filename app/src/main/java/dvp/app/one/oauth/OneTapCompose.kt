package dvp.app.one.oauth

import android.accounts.Account
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.UserRecoverableException
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun rememberOneTapSignInState(): OAuthState {
    return remember { OAuthState() }
}

@Composable
fun OneTapSignInWithGoogle(
    state: OAuthState,
    clientId: String,
    nonce: String? = null,
    onTokenIdReceived: (String) -> Unit,
    onDialogDismissed: (String) -> Unit,
) {
    val activity = LocalContext.current as Activity
    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        try {
            if (result.resultCode == Activity.RESULT_OK) {
                val oneTapClient = Identity.getSignInClient(activity)
                val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
                val tokenId = credentials.googleIdToken
                if (tokenId != null) {
                    onTokenIdReceived(tokenId)
                    state.getAccessToken()
                }
            } else {
                onDialogDismissed("Dialog Closed.")
                state.close()
            }
        } catch (e: ApiException) {
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    onDialogDismissed("Dialog Canceled.")
                    state.close()
                }
                CommonStatusCodes.NETWORK_ERROR -> {
                    onDialogDismissed("Network Error.")
                    state.close()
                }
                else -> {
                    onDialogDismissed(e.message.toString())
                    state.close()
                }
            }
        }
    }

    LaunchedEffect(key1 = state.state) {
        if (state.state == State.SignIn) {
            signIn(activity = activity,
                clientId = clientId,
                nonce = nonce,
                onSelectAccount = activityLauncher::launch,
                onError = {
                    onDialogDismissed(it)
                    state.close()
                })
        }
    }
}

private fun signIn(
    activity: Activity,
    clientId: String,
    nonce: String?,
    onSelectAccount: (IntentSenderRequest) -> Unit,
    onError: (String) -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setNonce(nonce)
                .setServerClientId(clientId)
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true).build()

    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            try {
                onSelectAccount(
                    IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                )
            } catch (e: Exception) {
                onError(e.message.toString())
            }
        }.addOnFailureListener {
            signUp(
                activity = activity,
                clientId = clientId,
                nonce = nonce,
                launchActivityResult = onSelectAccount,
                onError = onError
            )
        }
}

private fun signUp(
    activity: Activity,
    clientId: String,
    nonce: String?,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    onError: (String) -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
            .setNonce(nonce).setServerClientId(clientId).setFilterByAuthorizedAccounts(false)
            .build()
    ).build()

    oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
        try {
            launchActivityResult(
                IntentSenderRequest.Builder(
                    result.pendingIntent.intentSender
                ).build()
            )
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }.addOnFailureListener {
        onError("Google Account not Found.")
    }
}


@Composable
fun GetAccessToken(
    state: OAuthState,
    email: String,
    scopes: Array<String>,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
) {
    val activity = LocalContext.current as Activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        println("TEST: result = ${it.resultCode}")
        println("TEST: result = ${it.data}")
        state.close()
    }
    LaunchedEffect(key1 = state.state) {
        if (state.state == State.GetAccessToken) {
            val token = getAccessToken(
                activity,
                email,
                scopes,
                recover = launcher::launch
            )
            if (token == null) {
                onError.invoke("Get Access token failed")
            } else {
                onSuccess.invoke(token)
            }
            state.close()
        }
    }
}

private suspend fun getAccessToken(
    activity: Activity,
    email: String,
    scopes: Array<String>,
    recover: suspend (Intent) -> Unit,
) = withContext(Dispatchers.IO) {
    println("TEST: start get access token $email")
    checkForRecover(recover) {
        val token = GoogleAuthUtil.getToken(
            activity,
            Account(email, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE),
            "oauth2:" + scopes.joinToString(" ")
        )
        println("TEST: token = $token")
        GoogleAuthUtil.clearToken(activity, token)

        return@checkForRecover token
    }
}

private suspend fun <T> checkForRecover(
    recover: suspend (Intent) -> Unit,
    checked: MutableSet<String> = mutableSetOf(),
    block: suspend () -> T,
): T? {
    println("TEST: checkForRecover")
    return try {
        block()
    }
//    catch (e: UserRecoverableAuthIOException) {
//        val name = e::class.qualifiedName.orEmpty()
//        if (checked.contains(name)) throw e else checked.add(name)
//        recover(e.intent)
//        checkForRecover(recover, checked, block)
//    }
    catch (e: UserRecoverableAuthException) {
        println("TEST: UserRecoverableAuthException == $e")
        val name = e::class.qualifiedName.orEmpty()
        if (checked.contains(name)) throw e else checked.add(name)
        recover(e.intent!!)
        checkForRecover(recover, checked, block)
    } catch (e: UserRecoverableException) {
        println("TEST: UserRecoverableException == $e")
        val name = e::class.qualifiedName.orEmpty()
        if (checked.contains(name)) throw e else checked.add(name)
        recover(e.intent)
        checkForRecover(recover, checked, block)
    } catch (e: Exception) {
        println("TEST: Exception == $e")
        return null
    }
}