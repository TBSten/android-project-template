package dsl

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}
