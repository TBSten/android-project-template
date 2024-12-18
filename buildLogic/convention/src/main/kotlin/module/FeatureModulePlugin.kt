package module

import dsl.androidTestImplementation
import dsl.debugImplementation
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import primitive.ComposePlugin
import primitive.KotlinxSerializationPlugin

open class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(AndroidLibraryModulePlugin::class)
                apply(ComposePlugin::class)
                apply(KotlinxSerializationPlugin::class)
            }

            dependencies {
                implementation(libs.library("composeUi"))
                implementation(libs.library("composeUiGraphics"))
                implementation(libs.library("composeUiToolingPreview"))
                implementation(libs.library("composeMaterial3"))
                implementation(libs.library("navigationCompose"))

                testImplementation(libs.library("junit"))

                androidTestImplementation(libs.library("androidxJunit"))
                androidTestImplementation(libs.library("androidxEspressoCore"))
                androidTestImplementation(libs.library("composeUiTestJunit4"))

                debugImplementation(libs.library("composeUiTooling"))
                debugImplementation(libs.library("composeUiTestManifest"))
            }
        }
    }
}
