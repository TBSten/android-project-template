package your.projectPackage.feature.example.userList

import your.projectPackage.domain.example.user.User

internal sealed interface ExampleUserListUiState {
    data object InitialLoading : ExampleUserListUiState
    data class Success(
        val users: List<User>,
    ) : ExampleUserListUiState
}
