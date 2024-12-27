package primitive

import BuildOutputFiles
import dsl.alias
import dsl.ktlint
import dsl.libs
import dsl.plugin
import dsl.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

open class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                alias(libs.plugin("ktlint"))
            }

            ktlint {
                reporters {
                    reporter(ReporterType.SARIF)
                }
            }

            tasks.withType(GenerateReportsTask::class.java) {
                reportsOutputDirectory.set(
                    BuildOutputFiles(rootProject)
                        .ktlintReport(target),
                )
            }
        }
    }
}
