plugins {
    id ("android-app")
}

dependencies {
    implementation(project(":compose-main:browser-ui"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.ui.unit)

    implementation(libs.accompanist.pager)

    implementation(libs.espresso.core)
}