@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("config")
    id("compose")
    kotlin("android")
}

android {
    defaultConfig {
        applicationId = "dvp.app.one"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
