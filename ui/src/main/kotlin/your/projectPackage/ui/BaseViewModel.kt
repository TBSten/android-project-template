package your.projectPackage.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow

// TODO エラーハンドリング
abstract class BaseViewModel<State, Action> : ViewModel() {
    abstract val uiState: StateFlow<State>
    abstract fun dispatch(action: Action)

    open fun init() {}
}

@Composable
fun <State, Action, VM : BaseViewModel<State, Action>> consumeViewModel(viewModel: VM): Pair<State, (Action) -> Unit> {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleCreateEffect(onEvent = viewModel::init)

    return uiState to viewModel::dispatch
}
