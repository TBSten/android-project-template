import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "build.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    // TODO compileOnly に replace
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.kotlinGradlePluginApi)

    implementation(libs.androidGradlePlugin)
    implementation(libs.composeGradlePlugin)
    implementation(libs.ktlintGradlePlugin)
}

gradlePlugin {
    plugins {
        register("android.application") {
            id = "buildLogic.module.android.application"
            implementationClass = "module.AndroidApplicationModulePlugin"
        }

        register("lint") {
            id = "buildLogic.primitive.lint"
            implementationClass = "primitive.LintPlugin"
        }
        register("compose") {
            id = "buildLogic.primitive.compose"
            implementationClass = "primitive.ComposePlugin"
        }
    }
}
