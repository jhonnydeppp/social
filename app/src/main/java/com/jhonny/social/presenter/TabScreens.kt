package com.jhonny.social.presenter

import android.os.Parcelable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jhonny.social.presenter.detail.DetailScreen
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.favorites.FavoritesScreen
import com.jhonny.social.presenter.main.MainScreen
import com.jhonny.social.presenter.navigation.AppScreens
import com.jhonny.social.ui.theme.Blue80
import com.jhonny.social.util.parcelable

@Composable
fun TabbedScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        AppScreens.MainScreen,
        AppScreens.FavoritesScreen
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TabRow(
                backgroundColor = Blue80,
                selectedTabIndex = screens.indexOfFirst { screen ->
                    screen.route == currentRoute
                }.coerceAtLeast(0),
            ) {
                screens.forEachIndexed { index, screen ->
                    Tab(
                        text = { Text(screen.name) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.MainScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.MainScreen.route) {
                MainScreen(navController)
            }
            composable(AppScreens.FavoritesScreen.route) {
                FavoritesScreen(navController)
            }
            composable(route = AppScreens.DetailScreen.route + "/{user}", arguments =
            listOf(navArgument(name = "user") {
                type = NavType.StringType

            })
            ) {
                val user: Parcelable? =  it.arguments?.parcelable("user")
                val result =
                    navController.previousBackStackEntry?.savedStateHandle?.get<UserItemPresentation?>("user")
                DetailScreen(navController, result?: UserItemPresentation())

            }
        }
    }
}