package com.jhonny.social.presenter.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jhonny.punkbeer.R

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {

    IconButton(
        onClick = onClick,
        Modifier.testTag("Favorite Button"),
        content = {
            Icon(
                painter = painterResource(id = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline),
                contentDescription = "Favorite Icon",
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteButton() {
    FavoriteButton(false, {})
}