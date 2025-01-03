plugins {
    // build-logic primitive plugins
    alias(libs.plugins.buildLogicPrimitiveLint) apply false
    alias(libs.plugins.buildLogicPrimitiveCompose) apply false
    alias(libs.plugins.buildLogicPrimitiveNavigationCompose) apply false
    alias(libs.plugins.buildLogicPrimitiveKotlinxSerialization) apply false
    alias(libs.plugins.buildLogicPrimitiveHilt) apply false
    alias(libs.plugins.buildLogicPrimitiveRoom) apply false

    // build-logic module plugins
    alias(libs.plugins.buildLogicModuleAndroidApplication) apply false
    alias(libs.plugins.buildLogicModuleAndroidLibrary) apply false
    alias(libs.plugins.buildLogicModuleFeature) apply false

    // other plugins
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinCompose) apply false
    alias(libs.plugins.kotlinPluginSerialization) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.room) apply false
}
