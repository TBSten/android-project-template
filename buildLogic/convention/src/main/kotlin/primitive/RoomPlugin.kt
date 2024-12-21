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

open class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                alias(libs.plugin("ksp"))
            }
            dependencies {
                implementation(libs.library("roomRuntime"))
                ksp(libs.library("roomCompiler"))
                implementation(libs.library("roomKtx"))
            }
        }
    }
}
