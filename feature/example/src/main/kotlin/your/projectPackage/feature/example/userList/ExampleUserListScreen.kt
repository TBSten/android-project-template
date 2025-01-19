package your.projectPackage.feature.example.userList

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import your.projectPackage.domain.example.user.User
import your.projectPackage.domain.example.user.UserId
import your.projectPackage.feature.example.userList.component.UserListItem
import your.projectPackage.ui.Dispatch
import your.projectPackage.ui.PreviewRoot
import your.projectPackage.ui.ValuesPreviewParameterProvider
import your.projectPackage.ui.component.AppButton
import your.projectPackage.ui.consumeViewModel
import your.projectPackage.ui.error.handleUiEvent

@Composable
internal fun ExampleUserListScreen(
    modifier: Modifier = Modifier,
    viewModel: ExampleUserListViewModel = hiltViewModel(),
) {
    val (uiState, dispatch) = consumeViewModel(viewModel)

    ExampleUserListScreen(
        uiState = uiState,
        dispatch = dispatch,
        modifier = modifier,
    )
}

@Composable
private fun ExampleUserListScreen(
    uiState: ExampleUserListUiState,
    dispatch: Dispatch<ExampleUserListUiAction>,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Crossfade(
            if (uiState == ExampleUserListUiState.InitialLoading) uiState else null,
            label = "ExampleUserListScreen Loading Crossfade",
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
            uiState is ExampleUserListUiState.Success,
            label = "ExampleUserListScreen Success Crossfade",
            modifier = Modifier.padding(innerPadding),
        ) { isSuccess ->
            if (isSuccess && uiState is ExampleUserListUiState.Success) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        if (uiState.users.isEmpty()) {
                            item {
                                Text("ユーザがいません")
                            }
                        } else {
                            items(uiState.users) { user ->
                                UserListItem(
                                    user = user,
                                    onDelete = { dispatch(ExampleUserListUiAction.OnDeleteUser(user)) },
                                )
                            }
                        }

                        item {
                            AppButton(
                                modifier = Modifier.padding(top = 32.dp),
                                onClick = handleUiEvent { dispatch(ExampleUserListUiAction.OnAddUser) },
                            ) {
                                Text("適当なユーザを追加")
                            }
                        }
                    }
                }
            }
        }
    }
}

private class ExampleUserListUiStatePreviewParameterProvider :
    ValuesPreviewParameterProvider<ExampleUserListUiState>(
        ExampleUserListUiState.InitialLoading,
        ExampleUserListUiState.Success(
            users = listOf(),
        ),
        ExampleUserListUiState.Success(
            users = listOf(
                User(uid = UserId(123), name = "test 1"),
                User(uid = UserId(456), name = "test 2"),
            ),
        ),
    )

@Preview
@Composable
private fun ExampleUserListScreenPreview(
    @PreviewParameter(ExampleUserListUiStatePreviewParameterProvider::class)
    uiState: ExampleUserListUiState,
) = PreviewRoot {
    ExampleUserListScreen(
        uiState = uiState,
        dispatch = { },
    )
}
