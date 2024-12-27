package your.projectPackage.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import your.projectPackage.feature.example.Examples
import your.projectPackage.feature.example.examples

@Composable
internal fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Examples, modifier = modifier) {
        examples()
    }
}
