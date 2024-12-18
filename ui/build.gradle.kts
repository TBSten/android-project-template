plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
}

android {
    namespace = "${libs.versions.app.applicationId.get()}.ui"
}

dependencies {
    api(projects.ui.navigation)
    api(projects.ui.designSystem)
}
