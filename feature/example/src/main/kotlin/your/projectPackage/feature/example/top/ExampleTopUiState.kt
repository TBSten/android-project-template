package your.projectPackage.feature.example.top

internal sealed interface ExampleTopUiState {
    data object InitialLoading : ExampleTopUiState
    data class Success(val count: Int) : ExampleTopUiState
}
