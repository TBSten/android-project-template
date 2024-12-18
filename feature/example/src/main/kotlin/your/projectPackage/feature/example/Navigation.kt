package your.projectPackage.feature.example

import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable
import your.projectPackage.feature.example.top.ExampleTopScreen
import your.projectPackage.ui.navigation.Navigation
import your.projectPackage.ui.navigation.Screen
import your.projectPackage.ui.navigation.composable
import your.projectPackage.ui.navigation.navigation

@Serializable
data object Examples : Navigation

@Serializable
data object ExampleTop : Screen

fun NavGraphBuilder.examples() {
    navigation<Examples>(startDestination = ExampleTop) {
        composable<ExampleTop> {
            ExampleTopScreen()
        }
    }
}
