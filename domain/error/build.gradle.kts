plugins {
    alias(libs.plugins.buildLogicModuleAndroidLibrary)
    alias(libs.plugins.buildLogicPrimitiveHilt)
}

android {
    namespace = "${libs.versions.app.applicationId.get()}.domain.error"
}

dependencies {
}

// androidComponents {
//    beforeVariants { variantBuilder ->
//        // To check for a certain build type, use variantBuilder.buildType == "<buildType>"
//        if (variantBuilder.productFlavors.containsAll(listOf("api" to "minApi21", "mode" to "demo"))) {
//            // Gradle ignores any variants that satisfy the conditions above.
//            variantBuilder.enable = false
//        }
//    }
// }
