package com.jhonny.social.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

fun Context.showToast(message: String) =
    Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()


