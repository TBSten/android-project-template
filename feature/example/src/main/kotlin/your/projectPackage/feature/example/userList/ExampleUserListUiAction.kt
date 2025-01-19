package your.projectPackage.feature.example.userList

import your.projectPackage.domain.example.user.User

sealed interface ExampleUserListUiAction {
    data object OnAddUser : ExampleUserListUiAction
    data class OnDeleteUser(val user: User) : ExampleUserListUiAction
}
