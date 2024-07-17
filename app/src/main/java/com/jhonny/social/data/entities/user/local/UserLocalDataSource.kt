package com.jhonny.social.data.entities.user.local

import com.jhonny.social.data.entities.user.entities.ResultsItem

interface UserLocalDataSource {

    fun setLocalList(list: List<ResultsItem?>)

    fun getFavorites(): List<ResultsItem>

    fun addNewElementsToList(list: List<ResultsItem?>?)
}