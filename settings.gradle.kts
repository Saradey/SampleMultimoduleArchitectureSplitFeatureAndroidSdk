enableFeaturePreview("VERSION_CATALOGS")

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
    versionCatalogs {
        create("devLibs") {
            from(files("gradle/dev-libs.versions.toml"))
        }
        create("coreLibs") {
            from(files("gradle/core-libs.versions.toml"))
        }
        create("uiLibs") {
            from(files("gradle/ui-libs.versions.toml"))
        }
        create("androidLibs") {
            from(files("gradle/android-libs.versions.toml"))
        }
    }
}
rootProject.name = "SampleMultimoduleArchitectureSplitFeature"
include(":app")
include(":sources:engine:main-activity")
include(":sources:resources:theme")
include(":sources:resources:ui-kit")
include(":sources:engine:di-core")
include(":ui-sandbox")
include(":sources:engine:navigation")
include(":sources:features:splash-screen-api")
include(":sources:features:splash-screen-impl")
include(":sources:core:base-core")
include(":sources:features:bottom-menu-api")
include(":sources:features:bottom-menu-impl")
