package module

import dsl.implementation
import dsl.plugins
import dsl.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import primitive.ArrowKtOpticsPlugin

/**
 * 全てのモジュールに適用する設定
 */
internal fun Project.configureBasic() {
    plugins {
        apply(ArrowKtOpticsPlugin::class)
    }

    dependencies {
        if (!path.startsWith(":common")) {
            implementation(project(":common"))
        }

        if (!path.startsWith(":common:testing")) {
            testImplementation(project(":common:testing"))
        }
    }
}
