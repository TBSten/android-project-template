package your.projectPackage.feature.example.top

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import your.projectPackage.domain.example.User
import your.projectPackage.domain.example.UserId
import your.projectPackage.ui.Dispatch
import your.projectPackage.ui.PreviewRoot
import your.projectPackage.ui.ValuesPreviewParameterProvider
import your.projectPackage.ui.component.AppButton
import your.projectPackage.ui.consumeViewModel
import your.projectPackage.ui.error.handleUiEvent

@Composable
internal fun ExampleTopScreen(
    modifier: Modifier = Modifier,
    viewModel: ExampleTopViewModel = hiltViewModel(),
) {
    val (uiState, dispatch) = consumeViewModel(viewModel)

    ExampleTopScreen(
        uiState = uiState,
        dispatch = dispatch,
        modifier = modifier,
    )
}

@Composable
private fun ExampleTopScreen(
    uiState: ExampleTopUiState,
    dispatch: Dispatch<ExampleTopUiAction>,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Crossfade(
            if (uiState == ExampleTopUiState.InitialLoading) uiState else null,
            label = "ExampleTopScreen Loading Crossfade",
            modifier = Modifier.padding(innerPadding),
        ) { uiState ->
            uiState?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        Crossfade(
            uiState is ExampleTopUiState.Success,
            label = "ExampleTopScreen Success Crossfade",
        ) { isSuccess ->
            if (isSuccess && uiState is ExampleTopUiState.Success) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text("data: ${uiState.count}")
                    AppButton(onClick = handleUiEvent { dispatch(ExampleTopUiAction.OnButtonClick) }) {
                        Text("+")
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        if (uiState.users.isEmpty()) {
                            item {
                                Text("ユーザがいません")
                            }
                        } else {
                            items(uiState.users) { user ->
                                Row(
                                    Modifier
                                        .fillParentMaxWidth()
                                        .animateItem(),
                                ) {
                                    Column(Modifier.weight(1f)) {
                                        Text("${user.uid}", fontSize = 14.sp)
                                        Text(user.name ?: "<none>", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                    }
                                    IconButton(onClick = handleUiEvent { dispatch(ExampleTopUiAction.OnDeleteUser(user)) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "削除")
                                    }
                                }
                            }
                        }

                        item {
                            AppButton(onClick = handleUiEvent { dispatch(ExampleTopUiAction.OnAddUser) }) {
                                Text("適当なユーザを追加")
                            }
                        }
                    }
                }
            }
        }
    }
}

private class ExampleTopUiStatePreviewParameterProvider :
    ValuesPreviewParameterProvider<ExampleTopUiState>(
        ExampleTopUiState.InitialLoading,
        ExampleTopUiState.Success(
            count = 123456,
            users = listOf(
                User(uid = UserId(123), name = "test 1"),
                User(uid = UserId(456), name = "test 2"),
            ),
        ),
    )

@Preview
@Composable
private fun ExampleTopScreenPreview(
    @PreviewParameter(ExampleTopUiStatePreviewParameterProvider::class)
    uiState: ExampleTopUiState,
) = PreviewRoot {
    ExampleTopScreen(
        uiState = uiState,
        dispatch = { },
    )
}
