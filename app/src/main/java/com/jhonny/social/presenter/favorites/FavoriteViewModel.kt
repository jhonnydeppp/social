package com.jhonny.social.presenter.favorites

import com.jhonny.social.presenter.MainActivity
import com.jhonny.social.presenter.base.BaseViewModel
import com.jhonny.social.presenter.entities.UserItemPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : BaseViewModel() {
    private val _beer = MutableStateFlow<List<UserItemPresentation?>>(emptyList())
    val beer: StateFlow<List<UserItemPresentation?>> = _beer

    private val _errorHandling = MutableStateFlow(Exception())
    val errorHandling: StateFlow<Exception> = _errorHandling
    var name: String = ""

    fun getUsers() {
        _beer.value = MainActivity.FavoritesSingletonList.instance
    }

    fun isFavorite(user: UserItemPresentation) =
        _beer.value.find { it?.name?.first == user.name?.first }?.isFavorite

     fun updateFavoriteList(user: UserItemPresentation) {
         user.isFavorite = !user.isFavorite
         if(user.isFavorite) {
             if (MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first } == null)
                 MainActivity.FavoritesSingletonList.instance.add(user)
         }  else
             MainActivity.FavoritesSingletonList.instance.remove(MainActivity.FavoritesSingletonList.instance.find { user.name?.first == it.name?.first })
         _beer.value.find { it?.name?.first == user.name?.first }?.isFavorite = user.isFavorite
     }

    fun updateLocalList() {
        MainActivity.FavoritesSingletonList.instance.forEach{ favorite ->
            _beer.value.find { favorite.name?.first == it?.name?.first }?.isFavorite = true
        }
    }

}

