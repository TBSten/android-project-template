plugins {
    alias(libs.plugins.buildLogicModuleAndroidApplication)
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
    // In many cases, please add dependencies to `build-logic/convention/src/main/kotlin/module/AndroidApplicationModulePlugin` .
}
