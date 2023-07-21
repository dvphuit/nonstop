plugins {
    id("android-lib")
}

android {
    namespace = "dvp.lib.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(projects.libs.common)
}