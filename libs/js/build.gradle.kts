plugins {
    id("android-lib")
}


dependencies {
    implementation(libs.quickJs)
}
android {
    namespace = "dvp.lib.jsengine"
}
