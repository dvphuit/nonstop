plugins {
    id("android-lib")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    api(libs.koin)
    implementation(projects.libs.ytube)
}