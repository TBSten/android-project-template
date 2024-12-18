pluginManagement {
    includeBuild("./buildLogic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AndroidProjectTemplate"
include(":app")
include(":domain")
include(":data")
include(":data:api")
include(":data:database")
include(":data:preferences")
include(":ui")
include(":ui:designSystem")
include(":ui:navigation")
include(":debug")
include(":testing")
include(":lintRules:composeNavigation")
include(":feature:example")
