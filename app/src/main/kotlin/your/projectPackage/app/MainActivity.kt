package your.projectPackage.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import your.projectPackage.error.ApplicationErrorStateHolder
import your.projectPackage.ui.error.HandleErrors
import your.projectpackage.debug.ui.InjectDebugMenu
import your.projectpackage.ui.designSystem.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var applicationErrorStateHolder: ApplicationErrorStateHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                HandleErrors(applicationErrorStateHolder) {
                    InjectDebugMenu()
                    AppNavHost()
                }
            }
        }
    }
}
