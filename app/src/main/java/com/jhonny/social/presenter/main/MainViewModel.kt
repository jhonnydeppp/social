package com.jhonny.social.presenter.main



import com.jhonny.social.domain.usecases.AddUserFavoriteUseCase
import com.jhonny.social.domain.usecases.DeleteUserFavoriteUseCase
import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val addUserFavoriteUseCase: AddUserFavoriteUseCase,
    private val deleteUserFavoriteUseCase: DeleteUserFavoriteUseCase
) : BaseViewModel() {
    private val _user = MutableStateFlow<List<UserItemPresentation?>>(emptyList())
    val user: StateFlow<List<UserItemPresentation?>> = _user
    private val _favorites = MutableStateFlow<List<UserItemPresentation?>>(emptyList())
    val favorites: StateFlow<List<UserItemPresentation?>> = _favorites

    private val _errorHandling = MutableStateFlow(Exception())
    val errorHandling: StateFlow<Exception> = _errorHandling
    var name: String = ""
    private var textToSearch: String = ""
    private var page: Int = 1
    private var isUserCalled = false

    fun setUsers(user: List<UserItemPresentation>) {
        _user.value = user
    }

    fun getUsers() {
        if (!isUserCalled)
            if (textToSearch.isEmpty())
                launch {
                    withContext(IO) {
                        isUserCalled = true
                        getUserUseCase.execute(page).toPresentationResult().let { result ->
                            when {
                                result.isSuccess ->
                                    result.dataOrNull()?.let { domainUser ->
                                        val userPresentation = UserMapper().map(domainUser)
                                        _user.value += userPresentation.list ?: emptyList()
                                    }

                                result.isError -> {
                                    _errorHandling.value = result.getError()
                                }

                                else -> {}
                            }
                        }
                        page++
                        isUserCalled = false
                    }
                }
    }

    fun isFavorite(user: UserItemPresentation) =
        _user.value.find { it?.name?.first == user.name?.first }?.isFavorite

    fun updateFavoriteList(user: UserItemPresentation) {
        launch {
            withContext(IO) {
                if(user.isFavorite) {
                    addUserFavoriteUseCase.execute(UserMapper().mapToDomain(UserPresentation(list = listOf(user))))
                } else {
                    deleteUserFavoriteUseCase.execute(UserMapper().mapToDomain(UserPresentation(list = listOf(user))))
                    _user.value.find { user.name?.first == it?.name?.first }?.isFavorite = false
                }
            }
        }
    }

    fun getUserByName(userName: String) {
        _user.value = emptyList()
        textToSearch = userName
        if (textToSearch.isNotEmpty()) {
            launch {
                withContext(IO) {
                    getUserByNameUseCase.execute(userName).toPresentationResult().let { result ->
                            when {
                                result.isSuccess ->
                                    result.dataOrNull()?.let { userPresentation ->
                                        _user.value += (userPresentation.list as? List<*>)?.filterIsInstance<UserItemPresentation?>()
                                            ?: emptyList()
                                    }
                                result.isError -> {
                                    _errorHandling.value = result.getError()
                                }
                            }
                        }
                }
            }
        }
    }

}

