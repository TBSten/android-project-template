package module

import com.android.build.api.dsl.LibraryExtension
import dsl.alias
import dsl.androidTestImplementation
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.testImplementation
import dsl.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import primitive.LintPlugin

open class AndroidLibraryModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(LintPlugin::class)

                alias(libs.plugin("kotlinAndroid"))
                alias(libs.plugin("androidLibrary"))
            }

            androidLibrary {
                compileSdk = libs.version("app-compileSdk").toInt()

                defaultConfig {
                    compileSdk = libs.version("app-compileSdk").toInt()
                    minSdk = libs.version("app-minSdk").toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildTypes {
                    release {
                        isMinifyEnabled = false
                    }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            val kotlin = kotlinExtension as? KotlinAndroidProjectExtension
            kotlin?.compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }

            dependencies {
                implementation(libs.library("androidxCoreKtx"))
                implementation(libs.library("appcompat"))
                testImplementation(libs.library("junit"))
                androidTestImplementation(libs.library("androidxJunit"))
                androidTestImplementation(libs.library("androidxEspressoCore"))
            }
        }
    }
}

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}
