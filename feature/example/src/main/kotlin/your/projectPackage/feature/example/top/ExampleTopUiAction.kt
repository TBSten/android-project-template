package your.projectPackage.feature.example.top

import your.projectPackage.domain.example.User

sealed interface ExampleTopUiAction {
    data object OnButtonClick : ExampleTopUiAction
    data object OnAddUser : ExampleTopUiAction
    data class OnDeleteUser(val user: User) : ExampleTopUiAction
}
