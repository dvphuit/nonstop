plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("android-base")
}

dependencies {
    implementation(project(":libs:browser"))
    implementation (libs.androidx.core.ktx)
    implementation (libs.compose.ui)
    implementation (libs.compose.material)
    implementation (libs.compose.ui.tooling)
}