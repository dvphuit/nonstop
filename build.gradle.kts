buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath(":build-logic")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.kotlin.get()}")
//        classpath ("com.google.gms:google-services:4.3.14")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}