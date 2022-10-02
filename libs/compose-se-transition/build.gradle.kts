plugins {
    id("android-lib-compose")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.ui.unit)
}