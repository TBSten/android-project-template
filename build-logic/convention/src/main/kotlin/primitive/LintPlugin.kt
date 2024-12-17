package primitive

import dsl.alias
import dsl.ktlint
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

open class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                alias(libs.plugin("ktlint"))
            }

            ktlint {
            }

            tasks.withType(GenerateReportsTask::class.java) {
                reportsOutputDirectory.set(
                    rootProject.layout.buildDirectory.dir("ktlint-report"),
                )
            }
        }
    }
}
