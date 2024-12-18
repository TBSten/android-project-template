package dsl

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(artifact: Project) {
    add("implementation", artifact)
}

internal fun DependencyHandlerScope.implementation(artifact: Dependency) {
    add("implementation", artifact)
}

internal fun DependencyHandlerScope.debugImplementation(artifact: Dependency) {
    add("debugImplementation", artifact)
}

internal fun DependencyHandlerScope.testImplementation(artifact: Dependency) {
    add("debugImplementation", artifact)
}

internal fun DependencyHandlerScope.androidTestImplementation(artifact: Dependency) {
    add("androidTestImplementation", artifact)
}
