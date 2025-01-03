package module

import dsl.alias
import dsl.android
import dsl.androidTestImplementation
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.plugins
import dsl.testImplementation
import dsl.version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import primitive.LintPlugin

internal fun Project.configureAndroid() {
    val project = this

    plugins {
        alias(libs.plugin("kotlinAndroid"))
        apply(LintPlugin::class)
    }

    android {
        compileSdk = libs.version("app-compileSdk").toInt()

        defaultConfig {
            minSdk = libs.version("app-minSdk").toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        lint {
            baseline = file("lint-baseline.xml")
            abortOnError = true
        }
    }

    val kotlin = kotlinExtension as KotlinAndroidProjectExtension
    kotlin.compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }

    dependencies {
        implementation(libs.library("androidxCoreKtx"))
        implementation(libs.library("androidxLifecycleRuntimeKtx"))

        testImplementation(libs.library("junit"))

        androidTestImplementation(libs.library("androidxJunit"))
        androidTestImplementation(libs.library("androidxEspressoCore"))

        if (project.path != ":testing") {
            testImplementation(project(":testing"))
        }
    }
}
