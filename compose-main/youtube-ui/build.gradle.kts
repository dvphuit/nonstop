plugins {
    id("android-lib-compose")
    id("kotlin-parcelize")
}

android {
    namespace = "dvp.ui.youtube"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.composeUi)
    implementation(libs.bundles.media3)

    implementation(libs.coil)
    implementation(libs.bundles.koin)
    implementation(projects.core)
    api(projects.data.utubeData)
    implementation(projects.libs.common)

    implementation("com.bumble.appyx:core:1.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-service:2.6.1")
    debugImplementation(libs.compose.ui.tooling)
}