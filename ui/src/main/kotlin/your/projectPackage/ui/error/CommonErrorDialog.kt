package your.projectPackage.ui.error

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import your.projectPackage.error.ErrorHandleType
import your.projectPackage.error.ErrorState
import your.projectPackage.ui.component.AppButton

@Composable
internal fun CommonErrorDialog(
    errorState: ErrorState.HandleError,
    errorHandleType: ErrorHandleType.Dialog,
    onClose: () -> Unit,
    onRetry: () -> Unit,
) {
    // FIXME リトライ処理対応によって ErrorHandleType.Dialog.retry が null ではなくなるため Suppress を外すことができる
    @Suppress("SENSELESS_COMPARISON")
    val retryable = errorHandleType.retry != null

    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Text(errorState.exception.displayTitle)
        },
        text = errorState.exception.displayText?.let { { Text(it) } },
        confirmButton = {
            @Suppress("KotlinConstantConditions")
            if (retryable) {
                AppButton(onClick = onRetry) {
                    Text("再読み込み")
                }
            } else {
                AppButton(onClick = onClose) {
                    Text("閉じる")
                }
            }
        },
        dismissButton = @Suppress("KotlinConstantConditions") if (retryable) {
            {
                AppButton(onClick = onClose) {
                    Text("閉じる")
                }
            }
        } else {
            null
        },
    )
}

private val Throwable.displayTitle: String
    get() = "エラーが発生しました"

private val Throwable.displayText: String?
    get() = this.message

@Preview
@Composable
private fun CommonErrorDialogPreview() {
    val errorHandleType = ErrorHandleType.Dialog
    // TODO apply AppTheme
    CommonErrorDialog(
        errorState = ErrorState.HandleError(Exception("test exception"), errorHandleType),
        errorHandleType = errorHandleType,
        onClose = { },
        onRetry = { },
    )
}
