@file:Suppress("UnstableApiUsage")

plugins {
    id("android-lib")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
}
