package dsl

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType

internal val Project.android: CommonExtension<*, *, *, *, *, *>
    get() = extensions.findByType(LibraryExtension::class)
        ?: extensions.findByType(BaseAppModuleExtension::class)
        ?: error("Can not find `android` extension")

internal fun Project.android(block: CommonExtension<*, *, *, *, *, *>.() -> Unit) = android.apply(block)

internal fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}
