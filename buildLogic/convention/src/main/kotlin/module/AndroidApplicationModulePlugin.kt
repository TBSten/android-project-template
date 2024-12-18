package module

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import dsl.alias
import dsl.androidApplication
import dsl.androidTestImplementation
import dsl.debugImplementation
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
import primitive.ComposePlugin
import primitive.LintPlugin

open class AndroidApplicationModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                alias(libs.plugin("androidApplication"))
                alias(libs.plugin("kotlinAndroid"))
                apply(ComposePlugin::class)
                apply(LintPlugin::class)
            }

            androidApplication {
                compileSdk =
                    libs
                        .version("app-compileSdk")
                        .toInt()

                defaultConfig {
                    minSdk =
                        libs
                            .version("app-minSdk")
                            .toInt()
                    targetSdk =
                        libs
                            .version("app-targetSdk")
                            .toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
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

                lint {
                    baseline = file("lint-baseline.xml")
                }
            }

            val kotlin = kotlinExtension as KotlinAndroidProjectExtension
            kotlin.compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }

            dependencies {
                implementation(libs.library("androidxCoreKtx"))
                implementation(libs.library("androidxLifecycleRuntimeKtx"))

                implementation(libs.library("activityCompose"))
                implementation(libs.library("composeUi"))
                implementation(libs.library("composeUiGraphics"))
                implementation(libs.library("composeUiToolingPreview"))
                implementation(libs.library("composeMaterial3"))

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

private fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}
