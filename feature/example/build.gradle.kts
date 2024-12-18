plugins {
    alias(libs.plugins.buildLogicModuleFeature)
}

android {
    namespace = "${libs.versions.app.applicationId.get()}.feature.test"
}

dependencies {
}
