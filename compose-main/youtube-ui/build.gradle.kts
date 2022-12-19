plugins {
    id("android-lib-compose")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodelKtx)

    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.systemUi)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insets.ui)
    implementation(libs.accompanist.navigation.animation)

    api(projects.data.youtubeData)
}