plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
}
android {
    namespace = "${libs.versions.app.applicationId.get()}.ui.testingAnnotation"
}
