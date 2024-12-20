plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
}

android {
    namespace = "${libs.versions.app.applicationId.get()}.domain"
}

dependencies {
    api(projects.domain.error)
}
