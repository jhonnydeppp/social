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
    fun `test updateFavoriteList when user is favorite`() = runTest {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = true)

        val job = launch {
            // When
            viewModel.updateFavoriteList(user)
            viewModel.getUsers()

            // Then
            assert(!user.isFavorite)
            assert(viewModel.user.value.firstOrNull { it?.name?.first == firstName } == null)
        }
        // immediately cancel job
        job.cancel()

    }

   /* @Test
    fun `test updatePage`() = runBlockingTest {
        // Given
       val page = viewModel.getCurrentPage()

       // val job = launch {
            // When
            viewModel.getUsers()
            assert(page == (viewModel.getCurrentPage()+1))

       // }
        // immediately cancel job
     //   job.cancel()

    }*/
/*
    @Test
    fun `test updateFavoriteList when user is not in favorite`() = runBlockingTest {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = false)
        viewModel.setUsers(listOf(user))

        println("Hello StackOverflow")
        println("---> "+ viewModel.user.value.firstOrNull().toString())
        //log("---> "+ viewModel.user.value.firstOrNull().toString())
        viewModel.getUsers()
        println("---> "+ viewModel.user.value.firstOrNull().toString())
 /*       assert(user.isFavorite)
        assert(viewModel.user.value.firstOrNull { it?.name?.first == firstName } == user)
        */

        // Then
       // assert(user.isFavorite)
        println("---> "+ viewModel.user.value.firstOrNull().toString())

    }*/

}