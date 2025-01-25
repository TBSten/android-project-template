package your.projectPackage.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TODO("Please specify your app startDestination"),
        modifier = modifier
    ) {
        TODO("Please specify your app startDestination")
    }
}
