plugins {
    id("android-lib")
}

android {
    namespace = "dvp.lib.common"
}

dependencies {
    implementation(libs.logger)
    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx){
        exclude("androidx.lifecycle")
    }
}