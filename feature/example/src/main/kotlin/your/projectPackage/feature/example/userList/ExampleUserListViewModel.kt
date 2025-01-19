package your.projectPackage.feature.example.userList

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import your.projectPackage.domain.example.user.CreateUserUseCase
import your.projectPackage.domain.example.user.DeleteUserUseCase
import your.projectPackage.domain.example.user.GetUsersUseCase
import your.projectPackage.domain.example.user.User
import your.projectPackage.domain.example.user.UserId
import your.projectPackage.error.ApplicationErrorStateHolder
import your.projectPackage.ui.BaseViewModel

@HiltViewModel
internal class ExampleUserListViewModel @Inject constructor(
    applicationErrorStateHolder: ApplicationErrorStateHolder,
    private val getUsers: GetUsersUseCase,
    private val createUser: CreateUserUseCase,
    private val deleteUser: DeleteUserUseCase,
) : BaseViewModel<ExampleUserListUiState, ExampleUserListUiAction>(applicationErrorStateHolder) {
    private val _uiState = MutableStateFlow<ExampleUserListUiState>(ExampleUserListUiState.InitialLoading)
    override val uiState = _uiState.asStateFlow()

    override fun init() {
        viewModelScope.launchSafe {
            delay(1000)
            refresh()
        }
    }

    override fun dispatch(action: ExampleUserListUiAction) {
        viewModelScope.launchSafe {
            when (action) {
                ExampleUserListUiAction.OnAddUser -> onAddUser()
                is ExampleUserListUiAction.OnDeleteUser -> onDeleteUser(action.user)
            }
        }
    }

    private suspend fun onAddUser() {
        val randomId = Random.nextInt(1..1_000_000)
        createUser.execute(
            User(
                uid = UserId(randomId),
                name = "User-$randomId",
            ),
        )
        refresh()
    }

    private suspend fun onDeleteUser(user: User) {
        deleteUser.execute(user)
        refresh()
    }

    private suspend fun refresh() {
        val users = getUsers.execute()
        _uiState.update {
            ExampleUserListUiState.Success(
                users = users,
            )
        }
    }
}
