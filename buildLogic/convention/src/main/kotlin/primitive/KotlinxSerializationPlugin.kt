package primitive

import dsl.alias
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

open class KotlinxSerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                alias(libs.plugin("kotlinPluginSerialization"))
            }
            dependencies {
                implementation(libs.library("kotlinxSerializationJson"))
            }
        }
    }
}
