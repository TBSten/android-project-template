plugins {
    // build-logic primitive plugins
    alias(libs.plugins.buildLogicPrimitiveLint) apply false

    // build-logic convention plugins

    // other plugins
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinCompose) apply false
    alias(libs.plugins.ktlint) apply false
}
