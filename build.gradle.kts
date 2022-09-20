buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath(":build-logic")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}