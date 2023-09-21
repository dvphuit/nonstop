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

    ":data:utube-data",

    ":core",

    ":libs:js",
    ":libs:uclient",
    ":libs:browser",
    ":libs:common",
)

includeBuild("build-logic")