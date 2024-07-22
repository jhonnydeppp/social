package com.jhonny.social.presenter.navigation

sealed class AppScreens(val route: String, val  name: String) {
    object TabbedScreen: AppScreens("tabbed_screen","Tab Screen")
    object MainScreen: AppScreens("main_screen", "Users")
    object DetailScreen: AppScreens("detail_screen", "Detail")
    object FavoritesScreen: AppScreens("favorite_screen", "Favorites")
}
