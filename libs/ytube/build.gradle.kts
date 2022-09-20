plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("android-base")
}

dependencies {
    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.serialization)
    implementation(project(":libs:js"))
    testImplementation(libs.junit)
}