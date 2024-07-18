package com.jhonny.social.data.entities.user.local


import com.jhonny.social.data.entities.user.entities.ResultsItem
import com.jhonny.social.data.entities.user.local.preferences.UserLocalPreferencesDataSource
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val userLocalPreferencesDataSource: UserLocalPreferencesDataSource): UserLocalDataSource {

    override fun setLocalList(list: List<ResultsItem?>) {
        userLocalPreferencesDataSource.setLocalList(list)
    }

    override fun getFavorites(): List<ResultsItem> =
         userLocalPreferencesDataSource.getLocalList().filter { it.isFavorite }

    override fun getLocalList(): List<ResultsItem> =
         userLocalPreferencesDataSource.getLocalList()

    override fun addNewElementsToList(list: List<ResultsItem?>?) {
        list?.let {
            val newList: List<ResultsItem?> = (getFavorites() + list).distinctBy { it?.name?.first }
            setLocalList(newList)
        }
    }

}