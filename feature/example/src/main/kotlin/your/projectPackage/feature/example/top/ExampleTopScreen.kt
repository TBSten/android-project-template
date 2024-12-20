package your.projectPackage.feature.example.top

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import your.projectPackage.ui.component.AppButton
import your.projectPackage.ui.consumeViewModel

@Composable
internal fun ExampleTopScreen(
    viewModel: ExampleTopViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val (uiState, dispatch) = consumeViewModel(viewModel)

    ExampleTopScreen(
        uiState = uiState,
        dispatch = viewModel::dispatch,
        modifier = modifier,
    )
}

@Composable
private fun ExampleTopScreen(
    uiState: ExampleTopUiState,
    dispatch: (ExampleTopUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Crossfade(
            uiState,
            label = "ExampleTopScreen Crossfade",
            modifier = Modifier.padding(innerPadding),
        ) { uiState ->
            when (uiState) {
                ExampleTopUiState.InitialLoading ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                is ExampleTopUiState.Success ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("data: ${uiState.count}")
                        AppButton(onClick = { dispatch(ExampleTopUiAction.OnButtonClick) }) {
                            Text("+")
                        }
                    }
            }
        }
    }
}

private class ExampleTopUiStatePreviewParameterProvider : PreviewParameterProvider<ExampleTopUiState> {
    override val values = sequenceOf(
        ExampleTopUiState.InitialLoading,
        ExampleTopUiState.Success(123456),
    )
}

@Preview
@Composable
private fun ExampleTopScreenPreview(
    @PreviewParameter(ExampleTopUiStatePreviewParameterProvider::class)
    uiState: ExampleTopUiState,
) {
    // TODO apply app theme
    ExampleTopScreen(
        uiState = uiState,
        dispatch = { },
    )
}
