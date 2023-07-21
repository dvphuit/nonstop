plugins {
    id("android-lib-compose")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation (libs.androidx.core.ktx)
    implementation (libs.compose.ui)
    implementation (libs.compose.material)
    implementation (libs.compose.ui.tooling)
}
android {
    namespace = "dvp.lib.book"
}
