plugins {
    `java-library`
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.androidLintApi)
    compileOnly(libs.androidLintChecks)
}
