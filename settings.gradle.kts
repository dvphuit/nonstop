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
    ":libs:js",
    ":libs:ytube",
    ":libs:browser",
)
includeBuild("build-logic")