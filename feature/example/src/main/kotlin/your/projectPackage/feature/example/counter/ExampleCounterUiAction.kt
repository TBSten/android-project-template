package your.projectPackage.feature.example.counter

internal sealed interface ExampleCounterUiAction {
    data object Refresh : ExampleCounterUiAction
}
