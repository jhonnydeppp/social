package com.jhonny.social

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jhonny.social.domain.usecases.AddUserFavoriteUseCase
import com.jhonny.social.domain.usecases.DeleteUserFavoriteUseCase
import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
import com.jhonny.social.extensions.log
import com.jhonny.social.presenter.MainActivity
import com.jhonny.social.presenter.entities.Name
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.main.MainViewModel
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * This is a good test
 *
 * This is a complex test
 */
@ExperimentalCoroutinesApi
class UserUnitTest {

    private val firstName = "ana"
    private lateinit var viewModel: MainViewModel
    private val getUserUseCase: GetUserUseCase = mockk()
    private val getUserByNameUseCase: GetUserByNameUseCase = mockk()
    private val addUserFavoriteUseCase: AddUserFavoriteUseCase = mockk()
    private val deleteUserFavoriteUseCase: DeleteUserFavoriteUseCase = mockk()


    @Before
    fun setUp() {
        viewModel = MainViewModel(getUserUseCase, getUserByNameUseCase, addUserFavoriteUseCase, deleteUserFavoriteUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test user is favorite`()  {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = true)
        viewModel.setUsers(listOf(user))
            // When
        viewModel.isFavorite(user)
            // Then
        assert(viewModel.isFavorite(user) == true)
    }

    @Test
    fun `test user is not favorite`()  {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = false)
        viewModel.setUsers(listOf(user))

        // When
        viewModel.isFavorite(user)

        // Then
        assert(viewModel.isFavorite(user) == false)
    }


}