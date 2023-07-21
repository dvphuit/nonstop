@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

configure<BaseAppModuleExtension> {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

}
