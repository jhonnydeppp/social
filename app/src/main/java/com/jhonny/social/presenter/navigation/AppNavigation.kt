package com.jhonny.social.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhonny.social.presenter.TabbedScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.TabbedScreen.route) {
        composable(route = AppScreens.TabbedScreen.route) {
            TabbedScreen(navController)
        }
    }
}