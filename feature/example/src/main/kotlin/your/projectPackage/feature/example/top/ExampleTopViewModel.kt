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
import your.projectPackage.domain.example.User
import your.projectPackage.domain.example.UserId
import your.projectPackage.domain.example.UserRepository
import your.projectPackage.error.ApplicationErrorStateHolder
import your.projectPackage.ui.BaseViewModel

@HiltViewModel
internal class ExampleTopViewModel @Inject constructor(
    applicationErrorStateHolder: ApplicationErrorStateHolder,
    private val userRepository: UserRepository,
) : BaseViewModel<ExampleTopUiState, ExampleTopUiAction>(applicationErrorStateHolder) {
    private val _uiState = MutableStateFlow<ExampleTopUiState>(ExampleTopUiState.InitialLoading)
    override val uiState = _uiState.asStateFlow()

    override fun init() {
        viewModelScope.launchSafe {
            delay(1000)
            refresh()
        }
    }

    override fun dispatch(action: ExampleTopUiAction) {
        viewModelScope.launchSafe {
            when (action) {
                ExampleTopUiAction.OnButtonClick -> onButtonClick()
                ExampleTopUiAction.OnAddUser -> onAddUser()
                is ExampleTopUiAction.OnDeleteUser -> onDeleteUser(action.user)
            }
        }
    }

    private suspend fun onButtonClick() {
        refresh()
    }

    private suspend fun onAddUser() {
        val randomId = Random.nextInt(1..1_000_000)
        userRepository.createUser(
            User(
                uid = UserId(randomId),
                name = "User-$randomId",
            ),
        )
        refresh()
    }

    private suspend fun onDeleteUser(user: User) {
        userRepository.deleteUser(user)
        refresh()
    }

    private suspend fun refresh() {
        val users = userRepository.getUsers()
        _uiState.update {
            ExampleTopUiState.Success(
                count = Random.nextInt(10..1000),
                users = users,
            )
        }
    }
}
