plugins {
    id("android-lib")
    kotlin("plugin.serialization")
}

android {
    namespace = "dvp.lib.ytube"
}

dependencies {
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.serializationJson)
    api(libs.ktor.serialization)
    implementation(libs.krypto)
    implementation(project(":libs:js"))
    testImplementation(libs.junit)
}