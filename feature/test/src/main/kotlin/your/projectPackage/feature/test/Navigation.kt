package your.projectPackage.feature.test

import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable
import your.projectPackage.ui.navigation.Screen
import your.projectPackage.ui.navigation.composable

@Serializable
data object Test : Screen

fun NavGraphBuilder.test() {
    composable<Test> {
        TestScreen()
    }
}
