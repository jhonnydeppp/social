package com.jhonny.social.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    object FavoritesSingletonList {
        val instance: MutableList<UserItemPresentation> by lazy {
            mutableListOf()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppNavigation() }

    }
}
