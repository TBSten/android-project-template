import dsl.alias
import dsl.android
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.plugins
import dsl.roborazzi
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.dependencies
import primitive.RobolectricPlugin

open class RoborazziPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                alias(libs.plugin("roborazzi"))
                apply(RobolectricPlugin::class)
            }
            android {
                @Suppress("UnstableApiUsage")
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        all {
                            it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                        }
                    }
                }
            }
            dependencies {
                testImplementation(libs.library("roborazziCore"))
                testImplementation(libs.library("roborazziCompose"))
                testImplementation(libs.library("roborazziJunit"))
            }

            configureComposePreviewTests()
        }
    }
}

private fun Project.configureComposePreviewTests() {
    dependencies {
        testImplementation(libs.library("roborazziComposePreviewScannerSupport"))
        testImplementation(libs.library("composePreviewScanner"))
    }

    roborazzi {
        @Suppress("OPT_IN_USAGE")
        generateComposePreviewRobolectricTests {
            enable = true
            packages.set(provider { listOf(android.namespace) })
            robolectricConfig =
                mapOf(
                    "sdk" to "[32]",
                    "qualifiers" to "RobolectricDeviceQualifiers.Pixel5",
                )
            includePrivatePreviews = true
        }
    }
}
