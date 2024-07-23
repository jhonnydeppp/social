package com.jhonny.social.presenter.favorites

import com.jhonny.social.domain.usecases.AddUserFavoriteUseCase
import com.jhonny.social.domain.usecases.DeleteUserFavoriteUseCase
import com.jhonny.social.domain.usecases.GetUserFavoritesUseCase
import com.jhonny.social.extensions.launch
import com.jhonny.social.presenter.base.BaseViewModel
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.entities.UserPresentation
import com.jhonny.social.presenter.entities.dataOrNull
import com.jhonny.social.presenter.entities.getError
import com.jhonny.social.presenter.entities.isError
import com.jhonny.social.presenter.entities.isSuccess
import com.jhonny.social.presenter.mapper.UserMapper
import com.jhonny.social.presenter.mapper.toPresentationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getUserFavoritesUseCase: GetUserFavoritesUseCase,
                                            private val addUserFavoriteUseCase: AddUserFavoriteUseCase,
                                            private val deleteUserFavoriteUseCase: DeleteUserFavoriteUseCase
) : BaseViewModel() {
    private val _user = MutableStateFlow<List<UserItemPresentation?>>(emptyList())
    val user: StateFlow<List<UserItemPresentation?>> = _user

    private var isUserListUpdated = false
    private val _errorHandling = MutableStateFlow(Exception())
    val errorHandling: StateFlow<Exception> = _errorHandling

    fun getFavoriteUsers() {
        launch {
            withContext(IO) {
                getUserFavoritesUseCase.execute().toPresentationResult().let { result ->
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { domainUser ->
                                val userPresentation = UserMapper().map(domainUser)
                                _user.value = userPresentation.list ?: emptyList()
                                isUserListUpdated = true
                            }

                        result.isError -> {
                            _errorHandling.value = result.getError()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    fun isFavorite(user: UserItemPresentation): Boolean {
        runBlocking  {
                run repeatBlock@ {
                    repeat(6) {
                        if (!isUserListUpdated) {
                            delay(500)
                        } else {
                            return@repeatBlock
                        }
                    }
                }
            }

        return _user.value.find { it?.name?.first == user.name?.first }?.isFavorite ?: false
    }

    fun updateFavoriteList(user: UserItemPresentation) {
        user.isFavorite = !user.isFavorite
        launch {
            withContext(IO) {
                if (user.isFavorite) {
                    addUserFavoriteUseCase.execute(UserMapper().mapToDomain(UserPresentation(list = listOf(user))))
                } else {
                    deleteUserFavoriteUseCase.execute(UserMapper().mapToDomain(UserPresentation(list = listOf(user))))
                    getFavoriteUsers()
                }
            }
        }
    }
    

}

