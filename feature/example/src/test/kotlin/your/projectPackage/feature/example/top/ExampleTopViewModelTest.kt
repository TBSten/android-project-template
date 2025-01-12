package your.projectPackage.feature.example.top

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import your.projectPackage.domain.example.User
import your.projectPackage.domain.example.UserId
import your.projectPackage.domain.example.UserRepository
import your.projectPackage.error.ApplicationErrorStateHolder
import your.projectPackage.testing.CoroutineRule

internal class ExampleTopViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    val mockUsers = listOf(User(uid = UserId(123), name = "test"))
    val errorStateHolder = ApplicationErrorStateHolder()
    val mockUserRepository: UserRepository = mockk<UserRepository>()
        .also {
            coEvery { it.createUser() } returns Unit
            coEvery { it.getUsers() } returns mockUsers
        }
    val viewModel = run {
        ExampleTopViewModel(
            applicationErrorStateHolder = errorStateHolder,
            userRepository = mockUserRepository,
        )
    }

    @Test
    fun testLoad() = runTest {
        assertEquals(viewModel.uiState.value, ExampleTopUiState.InitialLoading)

        viewModel.init()

        waitRefresh()

        viewModel.uiState.value.also {
            assertIs<ExampleTopUiState.Success>(it)
            assertEquals(it.users, mockUsers)
            coVerify { mockUserRepository.getUsers() }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testAddUser() = runTest {
        viewModel.dispatch(ExampleTopUiAction.OnAddUser)
        advanceTimeBy(2.seconds)
        coVerify { mockUserRepository.createUser(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun TestScope.waitRefresh() {
        advanceTimeBy(2.seconds)
    }
}
