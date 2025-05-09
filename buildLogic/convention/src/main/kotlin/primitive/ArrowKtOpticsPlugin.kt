package primitive

import dsl.alias
import dsl.implementation
import dsl.ksp
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ArrowKtOpticsPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugin("ksp"))
        }

        dependencies {
            implementation(libs.library("arrowKtOptics"))
            ksp(libs.library("arrowKtOpticsKspPlugin"))
        }
    }
}
