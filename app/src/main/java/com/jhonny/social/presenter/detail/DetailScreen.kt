package com.jhonny.social.presenter.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.util.orEmpty
import com.jhonny.social.util.setStyleBold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, userDetail: UserItemPresentation) {
    Scaffold {
        Body(userDetail, navController)
    }
}

@Composable
fun Body(user: UserItemPresentation, navController: NavController) {
    UserDetail(user, navController)
}

@Composable
fun UserDetail(user: UserItemPresentation, navController: NavController) {
        Column(modifier = Modifier
            .heightIn(min = 128.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally) {
            Spacer(modifier = Modifier.weight(1.0F))
            AsyncImage(
                model = user.picture?.medium.orEmpty(),
                contentDescription = "user image",
                modifier = Modifier
                    .size(128.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Nombre: ", user.name?.first.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Genero: ", user.gender.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("País: ", user.location?.country.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Email: ", user.email.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Edad: ", user.dob?.age.orEmpty().toString()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Telefono: ", user.phone.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Movil: ", user.cell.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Codigo Postal: ", user.location?.postcode.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Ciudad: ", user.location?.city.orEmpty()))
            Spacer(modifier = Modifier.weight(10.0F))
            Spacer(modifier = Modifier.weight(1.0F))
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)

                ,onClick = {
                navController.popBackStack()
            }) {
                Text("Regresar")
            }
            Spacer(modifier = Modifier.weight(1.0F))
        }
}