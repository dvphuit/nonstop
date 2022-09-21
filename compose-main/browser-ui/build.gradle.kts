plugins {
    id("android-lib-compose")
}

dependencies {
    implementation(project(":libs:browser"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
}