plugins {
    id("android-lib")
}

dependencies {
    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.serialization)
    implementation(project(":libs:js"))
    testImplementation(libs.junit)
}