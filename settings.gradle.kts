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
include(":domain:error")
include(":data")
include(":data:api")
include(":data:database")
include(":data:preferences")
include(":ui")
include(":ui:designSystem")
include(":ui:navigation")
include(":common")
include(":tools:debug")
include(":common:testing")
include(":tools:lintRules:composeNavigation")
include(":ui:testing")
include(":ui:feature:example")
