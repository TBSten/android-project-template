package your.projectPackage.feature.example.top

import your.projectPackage.domain.example.User

internal sealed interface ExampleTopUiState {
    data object InitialLoading : ExampleTopUiState
    data class Success(
        val count: Int,
        val users: List<User>,
    ) : ExampleTopUiState
}
