import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(33)

    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

}

configurations.all {
    resolutionStrategy {
        this.enableDependencyVerification()
        eachDependency {
            when (requested.module.toString()) {
                "androidx.lifecycle:lifecycle-common" -> useVersion("2.6.1")
            }
        }
    }
}