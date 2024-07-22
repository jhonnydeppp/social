package com.jhonny.social.data.entities.user.local.preferences

import com.jhonny.social.data.entities.user.entities.ResultsItem

interface UserLocalPreferencesDataSource {

    fun setLocalList(list: List<ResultsItem?>)

    fun getLocalList(): List<ResultsItem>

}