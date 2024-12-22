package primitive

import RoborazziPlugin
import dsl.alias
import dsl.androidTestImplementation
import dsl.composeCompiler
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.dependencies

open class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                alias(libs.plugin("kotlinCompose"))
                apply(RoborazziPlugin::class)
            }

            composeCompiler {
                reportsDestination = layout.buildDirectory.dir("compose_compiler")
                stabilityConfigurationFile =
                    rootProject.layout.projectDirectory.file("compose_stability_config.conf")
            }

            dependencies {
                implementation(platform(libs.library("composeBom")))
                androidTestImplementation(platform(libs.library("composeBom")))
            }
        }
    }
}
