package com.jhonny.social.presenter.main

import com.jhonny.social.data.dataOrNull
import com.jhonny.social.data.getError
import com.jhonny.social.data.isError
import com.jhonny.social.data.isSuccess
import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
import com.jhonny.social.extensions.launch
import com.jhonny.social.presenter.MainActivity
import com.jhonny.social.presenter.base.BaseViewModel
import com.jhonny.social.presenter.entities.UserItemPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserByNameUseCase: GetUserByNameUseCase,
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

    fun getDrinks() = user
    fun setDrinks(beer: List<UserItemPresentation>){
        _user.value = beer
    }

    fun getUsers() {
        if (!isUserCalled)
        if (textToSearch.isEmpty())
            launch {
                isUserCalled = true
                getUserUseCase(page).let { result ->
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { userPresentation ->
                                _user.value += (userPresentation.list ?: emptyList())
                                updateLocalList()
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
    fun isFavorite(user: UserItemPresentation) =
        _user.value.find { it?.name?.first == user.name?.first }?.isFavorite

     fun updateFavoriteList(user: UserItemPresentation) {
         user.isFavorite = !user.isFavorite
         if(user.isFavorite) {
             if (MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first } == null)
                 MainActivity.FavoritesSingletonList.instance.add(user)
         }  else
             MainActivity.FavoritesSingletonList.instance.remove(MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first })
         _user.value.find { it?.name?.first == user.name?.first }?.isFavorite = user.isFavorite
     }

    fun updateLocalList() {
        MainActivity.FavoritesSingletonList.instance.forEach{ favorite ->
            _user.value.find { favorite.name?.first == it?.name?.first }?.isFavorite = true
        }
    }

            fun geBeersByName(beerName: String) {
                _user.value = emptyList()
                textToSearch = beerName
                if (textToSearch.isNotEmpty()) {
                    launch {
                        getUserByNameUseCase(beerName).let { result ->
                            when {
                                result.isSuccess ->
                                    result.dataOrNull()?.let { beerPresentation ->
                                        _user.value = beerPresentation.list ?: emptyList()
                                        updateLocalList()
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

