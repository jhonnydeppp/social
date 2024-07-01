package com.jhonny.social.presenter.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jhonny.social.extensions.isScrolledToTheEnd
import com.jhonny.social.presenter.composables.FavoriteButton
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.navigation.AppScreens
import com.jhonny.social.ui.theme.MainTheme
import com.jhonny.social.util.setStyleBold

private lateinit var viewModel: FavoriteViewModel
@Composable
fun FavoritesScreen(navController: NavController) {
    viewModel = hiltViewModel()
    viewModel.getUsers()
    val userList by viewModel.user.collectAsState()
    val errorHandling by viewModel.errorHandling.collectAsState()
    MainTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (errorHandling.message != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://cdn0.iconfinder.com/data/icons/tools-41/432/not-found-512.png",
                            contentDescription = "empty image",
                            modifier = Modifier
                                .size(128.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }
                } else {
                    UserList(userList, navController)
                }
            }
        }
    }
}

@Composable
fun UserList(userList: List<UserItemPresentation?>, navController: NavController?) {
    val state = rememberLazyListState()
    LazyColumn(state = state, modifier = Modifier.padding(start = 16.dp, end = 8.dp) ) {
        items(userList) { userItem ->
            UserItem(userItem, navController)
        }
    }
    if(state.isScrolledToTheEnd()){
        // load more paginated
    }
}

@Composable
fun UserItem(user: UserItemPresentation?, navController: NavController?) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clickable {
                navController?.currentBackStackEntry?.savedStateHandle?.set(
                    key = "user",
                    value = user
                )
                navController?.navigate("${AppScreens.DetailScreen.route}/${user?.name?.first}")
            }
    ) {

        AsyncImage(
            model = user?.picture?.medium.orEmpty(),
            contentDescription = "user image",
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(24.dp))
        )


        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .heightIn(min = 128.dp)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Nombre: ", user?.name?.first.orEmpty()))
            Spacer(modifier = Modifier.weight(0.5F))
            Text(setStyleBold("Genero: ", user?.gender.orEmpty()))
            Spacer(modifier = Modifier.weight(0.5F))
            Text(setStyleBold("País: ", user?.location?.country.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                user?.let {
                    FavoriteButton(viewModel.isFavorite(user)?: false) {
                        viewModel.updateFavoriteList(user)
                        viewModel.updateLocalList()
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1.0F))
        }
    }
}