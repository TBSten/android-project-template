package your.projectPackage.ui.error

import androidx.compose.runtime.compositionLocalOf
import your.projectPackage.error.ErrorStateHolder

internal val LocalErrorStateHolder = compositionLocalOf<ErrorStateHolder> {
    error("ErrorStateHolder is not provided.")
}
