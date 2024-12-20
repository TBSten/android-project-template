package your.projectPackage.feature.example.top

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import your.projectPackage.ui.BaseViewModel

@HiltViewModel
internal class ExampleTopViewModel @Inject constructor() : BaseViewModel<ExampleTopUiState, ExampleTopUiAction>() {
    private val _uiState = MutableStateFlow<ExampleTopUiState>(ExampleTopUiState.InitialLoading)
    override val uiState = _uiState.asStateFlow()

    override fun init() {
        // TODO エラーハンドリング
        viewModelScope.launch {
            delay(1000)
            _uiState.update {
                ExampleTopUiState.Success(count = Random.nextInt(10..1000))
            }
        }
    }

    override fun dispatch(action: ExampleTopUiAction) = when (action) {
        ExampleTopUiAction.OnButtonClick -> onButtonClick()
    }

    private fun onButtonClick() {
        _uiState.update {
            ExampleTopUiState.Success(count = Random.nextInt(10..1000))
        }
    }
}
