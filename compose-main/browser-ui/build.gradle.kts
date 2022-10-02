plugins {
    id("android-lib-compose")
}

dependencies {
    api(project(":libs:browser"))
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
    implementation(project(":libs:compose-se-transition"))
}