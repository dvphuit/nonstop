plugins {
    id("android-lib")
    kotlin("plugin.serialization")
}

android {
    namespace = "dvp.data.youtube"
}

dependencies {
    implementation(libs.bundles.media3)
    implementation(libs.coil)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.bundles.koin)
    api(libs.compose.paging)
    api(projects.libs.uclient)
    implementation(projects.core)
    implementation(projects.libs.common)
    implementation("androidx.media3:media3-session:1.1.0")
    testImplementation(libs.junit)
}
