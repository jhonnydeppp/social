package com.jhonny.social.data.entities.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesUtils @Inject constructor(private val preferences: SharedPreferences) {

    fun setStringPreference(key: String , value: String) {
        preferences.edit().putString(key,value).apply()
    }

    fun getStringPreference(key: String) =
        preferences.getString(key,"")

}