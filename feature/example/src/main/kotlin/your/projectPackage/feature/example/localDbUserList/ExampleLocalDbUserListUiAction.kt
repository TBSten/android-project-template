package your.projectPackage.feature.example.localDbUserList

import your.projectPackage.domain.example.user.User

sealed interface ExampleLocalDbUserListUiAction {
    data object OnAddUser : ExampleLocalDbUserListUiAction
    data class OnDeleteUser(val user: User) : ExampleLocalDbUserListUiAction
}
