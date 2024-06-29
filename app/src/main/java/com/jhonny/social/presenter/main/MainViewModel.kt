package com.jhonny.social.presenter.main



import android.util.Log
import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
import com.jhonny.social.extensions.launch
import com.jhonny.social.presenter.MainActivity
import com.jhonny.social.presenter.base.BaseViewModel
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.errors.dataOrNull
import com.jhonny.social.presenter.errors.getData
import com.jhonny.social.presenter.errors.getError
import com.jhonny.social.presenter.errors.isError
import com.jhonny.social.presenter.errors.isSuccess
import com.jhonny.social.presenter.mapper.UserMapper
import com.jhonny.social.presenter.mapper.toPresentationResult
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
                getUserUseCase.execute(page).toPresentationResult().let { result ->
                    Log.i("UserRemoteDataSourceImpl", "getUsers: $result")
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { domainUser ->
                                val userPresentation =UserMapper().map(domainUser)
                                Log.i("UserRemoteDataSourceImpl", "userPresentation.list: ${userPresentation.list}")
                                _user.value += userPresentation.list ?: emptyList()
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
                        getUserByNameUseCase.execute(beerName).toPresentationResult().let { result ->
                            when {
                                result.isSuccess ->
                                    result.dataOrNull()?.let { beerPresentation ->
                                        _user.value += (beerPresentation.list as? List<*>)?.filterIsInstance<UserItemPresentation?>() ?: emptyList()
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

