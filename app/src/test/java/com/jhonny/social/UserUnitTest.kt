package com.jhonny.social

import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
import com.jhonny.social.presenter.MainActivity
import com.jhonny.social.presenter.entities.Name
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.main.MainViewModel
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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

    @Before
    fun setUp() {
        viewModel = MainViewModel(getUserUseCase, getUserByNameUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test updateFavoriteList when user is favorite`() = runBlockingTest {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = true)
        MainActivity.FavoritesSingletonList.instance.add(user)

        // When
        viewModel.updateFavoriteList(user)

        // Then
        assert(!user.isFavorite)
        assert(MainActivity.FavoritesSingletonList.instance.firstOrNull { it.name?.first == firstName } == null)
    }

    @Test
    fun `test updateFavoriteList when user is not in favorite`() = runBlockingTest {
        // Given
        val user = UserItemPresentation(name = Name(first = firstName), isFavorite = false)
        viewModel.setDrinks(listOf(user))

        // When
        viewModel.updateFavoriteList(user)

        // Then
        assert(user.isFavorite)
        assert(MainActivity.FavoritesSingletonList.instance.firstOrNull { it.name?.first == firstName } == user)
    }

}