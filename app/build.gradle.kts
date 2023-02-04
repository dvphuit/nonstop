plugins {
    id ("android-app")
//    id ("com.google.gms.google-services")
}

dependencies {
    implementation(project(":compose-main:book-ui"))
    implementation(project(":compose-main:browser-ui"))
    implementation(project(":compose-main:youtube-ui"))
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation(libs.googleSignin)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.ui.unit)

    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.systemUi)
//    implementation(libs.accompanist.insets)
//    implementation(libs.accompanist.insets.ui)

    implementation(libs.espresso.core)
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
