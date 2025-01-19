package your.projectPackage.feature.example.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import your.projectPackage.ui.Dispatch
import your.projectPackage.ui.PreviewRoot
import your.projectPackage.ui.component.AppButton
import your.projectPackage.ui.consumeViewModel

@Composable
internal fun ExampleCounterScreen(
    navigateToUserList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExampleCounterViewModel = hiltViewModel(),
) {
    val (uiState, dispatch) = consumeViewModel(viewModel)

    ExampleCounterScreen(
        uiState = uiState,
        dispatch = dispatch,
        modifier = modifier,
        navigateToUserList = navigateToUserList,
    )
}

@Composable
private fun ExampleCounterScreen(
    uiState: ExampleCounterUiState,
    dispatch: Dispatch<ExampleCounterUiAction>,
    navigateToUserList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("count: ${uiState.count}")

            AppButton(onClick = { dispatch(ExampleCounterUiAction.Refresh) }) {
                Text("refresh")
            }
            AppButton(onClick = { navigateToUserList() }) {
                Text("Go to UserList")
            }
        }
    }
}

@Preview
@Composable
private fun ExampleCounterScreenPreview() = PreviewRoot {
    ExampleCounterScreen(
        uiState = ExampleCounterUiState(
            count = 100,
        ),
        dispatch = { },
        navigateToUserList = {},
    )
}
