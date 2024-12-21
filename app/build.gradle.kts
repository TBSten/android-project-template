plugins {
    alias(libs.plugins.buildLogicModuleAndroidApplication)
    alias(libs.plugins.buildLogicPrimitiveNavigationCompose)
}

android {
    namespace =
        libs.versions.app.applicationId
            .get()

    defaultConfig {
        applicationId =
            libs.versions.app.applicationId
                .get()
        versionCode =
            libs.versions.app.versionCode
                .get()
                .toInt()
        versionName =
            libs.versions.app.versionName
                .get()
    }
}

dependencies {
    // feature modules
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.ui)
    implementation(projects.feature.example)
}
