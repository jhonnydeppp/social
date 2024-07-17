package com.jhonny.social.presenter.favorites

import com.jhonny.social.domain.usecases.AddUserFavoriteUseCase
import com.jhonny.social.domain.usecases.GetUserFavoritesUseCase
import com.jhonny.social.extensions.launch
import com.jhonny.social.presenter.MainActivity
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
class FavoriteViewModel @Inject constructor(private val getUserFavoritesUseCase: GetUserFavoritesUseCase,
                                            private val addUserFavoriteUseCase: AddUserFavoriteUseCase) : BaseViewModel() {
    private val _user = MutableStateFlow<List<UserItemPresentation?>>(emptyList())
    val user: StateFlow<List<UserItemPresentation?>> = _user

    private val _errorHandling = MutableStateFlow(Exception())
    val errorHandling: StateFlow<Exception> = _errorHandling
    var name: String = ""

    fun getUsers() {
        launch {
            withContext(IO) {
                getUserFavoritesUseCase.execute().toPresentationResult().let { result ->
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { domainUser ->
                                val userPresentation = UserMapper().map(domainUser)
                                _user.value = userPresentation.list ?: emptyList()
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

    fun isFavorite(user: UserItemPresentation) =
        _user.value.find { it?.name?.first == user.name?.first }?.isFavorite

    /* fun updateFavoriteList(user: UserItemPresentation) {
         user.isFavorite = !user.isFavorite
         if(user.isFavorite) {
             if (MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first } == null)
                 MainActivity.FavoritesSingletonList.instance.add(user)
         }  else
             MainActivity.FavoritesSingletonList.instance.remove(MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first })
         _user.value.find { it?.name?.first == user.name?.first }?.isFavorite = user.isFavorite
     }*/

    fun updateFavoriteList(user: UserItemPresentation) {
        user.isFavorite = !user.isFavorite
        launch {
            withContext(IO) {
            if(user.isFavorite)
                addUserFavoriteUseCase.execute(UserMapper().mapToDomain(UserPresentation(list = listOf(user))))
            }
        }
    }

    fun updateLocalList() {
    }

}

