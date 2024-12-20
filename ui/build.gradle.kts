plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
    alias(libs.plugins.buildLogicPrimitiveCompose)
}

android {
    namespace = "${libs.versions.app.applicationId.get()}.ui"
}

dependencies {
    api(projects.ui.navigation)
    api(projects.ui.designSystem)

    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial3)
}
