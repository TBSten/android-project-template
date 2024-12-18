package your.projectPackage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import your.projectPackage.feature.test.Test
import your.projectPackage.feature.test.test

@Composable
internal fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Test, modifier = modifier) {
        test()
    }
}
