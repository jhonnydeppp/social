package com.jhonny.social.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonny.social.util.DELAY_500L
import com.jhonny.social.util.DELAY_ML_SHORT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(block = block)

fun <T> throttleFirst(
    skipMs: Long = DELAY_ML_SHORT,
    coroutineScope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    return { param: T ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                destinationFunction(param)
                delay(skipMs)
            }
        }
    }
}

fun <T> debounce(
    waitMs: Long = DELAY_500L,
    coroutineScope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            destinationFunction(param)
        }
    }
}
