package your.projectPackage.ui.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.dropUnlessResumed
import your.projectPackage.error.LaunchSafe

@Composable
fun handleUiEvent(block: suspend () -> Unit): () -> Unit {
    val coroutineScope = rememberCoroutineScope()
    val errorStateHolder = LocalErrorStateHolder.current
    val launchSafe = remember(errorStateHolder) { LaunchSafe(errorStateHolder) }
    return dropUnlessResumed {
        with(launchSafe) {
            coroutineScope.launchSafe { block() }
        }
    }
}
