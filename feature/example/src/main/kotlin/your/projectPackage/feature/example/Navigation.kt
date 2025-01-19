package your.projectPackage.feature.example

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable
import your.projectPackage.feature.example.counter.ExampleCounterScreen
import your.projectPackage.feature.example.userList.ExampleUserListScreen
import your.projectPackage.ui.navigation.Navigation
import your.projectPackage.ui.navigation.Screen
import your.projectPackage.ui.navigation.composable
import your.projectPackage.ui.navigation.navigation

@Serializable
data object Examples : Navigation

@Serializable
data object ExampleCounter : Screen

@Serializable
data object ExampleUserList : Screen

fun NavGraphBuilder.examples(
    navController: NavController,
) {
    navigation<Examples>(startDestination = ExampleCounter) {
        composable<ExampleCounter> {
            ExampleCounterScreen(
                navigateToUserList = { navController.navigate(ExampleUserList) },
            )
        }
        composable<ExampleUserList> {
            ExampleUserListScreen()
        }
    }
}
