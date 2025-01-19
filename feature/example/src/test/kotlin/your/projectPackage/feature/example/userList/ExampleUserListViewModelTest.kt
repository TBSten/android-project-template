package your.projectPackage.feature.example.userList

import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import your.projectPackage.domain.example.user.User
import your.projectPackage.domain.example.user.UserId
import your.projectPackage.domain.example.user.UserRepository
import your.projectPackage.error.ApplicationErrorStateHolder
import your.projectPackage.testing.CoroutineRule

internal class ExampleUserListViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUiStateInit() = runTest {
        val userList = listOf(
            User(uid = UserId(123), name = "test 1"),
            User(uid = UserId(456), name = "test 2"),
        )
        val viewModel: ExampleUserListViewModel = run {
            val errorStateHolder = ApplicationErrorStateHolder()
            val userRepository: UserRepository = mockk<UserRepository>().also {
                coEvery { it.getUsers() } returns userList
            }
            ExampleUserListViewModel(errorStateHolder, userRepository)
        }

        assertEquals(viewModel.uiState.value, ExampleUserListUiState.InitialLoading)

        viewModel.init()
        advanceTimeBy(1001)

        val uiStateAfterInit = viewModel.uiState.value
        assertIs<ExampleUserListUiState.Success>(uiStateAfterInit)
        assertEquals(uiStateAfterInit.users, userList)
    }
}
