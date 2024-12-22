package your.projectPackage.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import your.projectPackage.error.AbstractErrorStateHolder
import your.projectPackage.ui.error.LocalErrorStateHolder

@Composable
fun PreviewRoot(content: @Composable () -> Unit) {
    // TODO apply AppTheme
    CompositionLocalProvider(
        LocalErrorStateHolder provides PreviewErrorStateHolder,
    ) {
        content()
    }
}

internal object PreviewErrorStateHolder : AbstractErrorStateHolder()
