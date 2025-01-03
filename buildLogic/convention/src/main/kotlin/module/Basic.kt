package module

import dsl.implementation
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
    }
}
