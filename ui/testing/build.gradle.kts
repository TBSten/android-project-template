import primitive.setUpCompose

plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
}

setUpCompose()

android {
    namespace = "${libs.versions.app.applicationId.get()}.ui.testing"
}

dependencies {
    implementation(platform(libs.composeBom))
    implementation(libs.kotlinxCoroutinesTest)
    implementation(libs.androidxEspressoCore)
    implementation(libs.composeUiTestJunit4)
    implementation(libs.composeUiTestManifest)
    implementation(libs.roborazziCore)
    implementation(libs.roborazziCompose)
    implementation(libs.roborazziJunit)
    implementation(libs.roborazziComposePreviewScannerSupport)
    implementation(libs.composePreviewScanner)
}
