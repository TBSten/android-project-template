plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)

    alias(libs.plugins.buildLogicPrimitiveLint)
    alias(libs.plugins.buildLogicPrimitiveCompose)
}

android {
    namespace =
        libs.versions.app.applicationId
            .get()
    compileSdk =
        libs.versions.app.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId =
            libs.versions.app.applicationId
                .get()
        minSdk =
            libs.versions.app.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.app.targetSdk
                .get()
                .toInt()
        versionCode =
            libs.versions.app.versionCode
                .get()
                .toInt()
        versionName =
            libs.versions.app.versionName
                .get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.activityCompose)
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)
    androidTestImplementation(platform(libs.composeBom))
    androidTestImplementation(libs.composeUiTestJunit4)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}
