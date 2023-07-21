enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "one"
include(
    ":app",

    ":compose-main:youtube-ui",
    ":compose-main:browser-ui",
    ":compose-main:book-ui",

    ":data:youtube-data",

    ":core",

    ":libs:js",
    ":libs:ytube",
    ":libs:browser",
    ":libs:common",
)

includeBuild("build-logic")
