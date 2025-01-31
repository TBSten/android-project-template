package module

import dsl.implementation
import dsl.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * 全てのモジュールに適用する設定
 */
internal fun Project.configureBasic() {
    dependencies {
        if (path != ":common") {
            implementation(project(":common"))
        }

        if (project.path != ":testing") {
            testImplementation(project(":common:testing"))
        }
    }
}
