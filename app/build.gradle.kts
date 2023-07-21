plugins {
    id ("android-app")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(project(":compose-main:book-ui"))
    implementation(project(":compose-main:browser-ui"))
    implementation(project(":compose-main:youtube-ui"))
    implementation(libs.googleSignin)
//    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.accompanist.webview)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.googlefonts)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.ui.unit)

    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.systemUi)
//    implementation(libs.accompanist.insets)
//    implementation(libs.accompanist.insets.ui)

    implementation(libs.espresso.core)

    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.android)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.serializationJson)
    implementation(libs.bundles.koin)
    implementation(projects.core)
    implementation(libs.ktor.serialization)

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.bumble.appyx:core:1.2.0")
}
android {
    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName("release")
//            isDebuggable = false
//        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    namespace = "dvp.app.one"

//    signingConfigs {
//        this.getByName("debug"){
////            storeFile = rootProject.file("env/debug.key")
////            keyAlias = "oneapp"
////            keyPassword = "oneapp"
////            storePassword = "oneapp"
//
//            storeFile = file("/Users/dvp/.android/debug.keystore")
//            keyAlias = "androiddebugkey"
//            keyPassword = "android"
//            storePassword = "android"
//        }
//    }
}
