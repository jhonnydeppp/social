package com.jhonny.social.data.entities.user.local.preferences


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jhonny.social.data.entities.preferences.PreferencesUtils
import com.jhonny.social.data.entities.user.entities.ResultsItem
import com.jhonny.social.util.FAVORITES
import java.lang.reflect.Type
import javax.inject.Inject


class UserLocalPreferencesDataSourceImpl @Inject constructor(private val preferencesUtils: PreferencesUtils): UserLocalPreferencesDataSource {

    override fun setFavorites(list: List<ResultsItem?>) {
        val arrayData = Gson().toJson(list)
        preferencesUtils.setStringPreference(FAVORITES, arrayData)
    }

    override fun getFavorites(): List<ResultsItem> {
        val favoritesString = preferencesUtils.getStringPreference(FAVORITES)
        var resultsItemList: List<ResultsItem> = emptyList<ResultsItem>()
        if (!favoritesString.isNullOrEmpty()) {
            val listType: Type = object : TypeToken<ArrayList<ResultsItem?>?>() {}.type
            resultsItemList = Gson().fromJson(favoritesString, listType)
        }
        return resultsItemList
    }
}