plugins {
    id("android-lib")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.serializationJson)
    api(libs.ktor.serialization)
    implementation(project(":libs:js"))
    testImplementation(libs.junit)
}