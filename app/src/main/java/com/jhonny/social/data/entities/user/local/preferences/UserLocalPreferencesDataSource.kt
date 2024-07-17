package com.jhonny.social.data.entities.user.local.preferences

import com.jhonny.social.data.entities.user.entities.ResultsItem

interface UserLocalPreferencesDataSource {

    fun setFavorites(list: List<ResultsItem?>)

    fun getFavorites(): List<ResultsItem>

}