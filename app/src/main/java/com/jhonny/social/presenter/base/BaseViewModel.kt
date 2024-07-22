package com.jhonny.social.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonny.punkbeer.R
import com.jhonny.social.base.BaseEvent
import com.jhonny.social.data.entities.Failure
import com.jhonny.social.extensions.launch
import com.jhonny.social.extensions.log
import com.jhonny.social.extensions.throttleFirst
import com.jhonny.social.presenter.base.entities.ActionResultMessage
import com.jhonny.social.presenter.base.entities.ActionResultPresentation
import com.jhonny.social.util.DELAY_500L
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.receiveAsFlow


open class BaseViewModel : ViewModel() {

    private val _event = Channel<BaseEvent>(Channel.BUFFERED)
    val event get() = _event.receiveAsFlow()

    protected fun emitEvent(event: BaseEvent) {
        _event.trySendBlocking(event)
    }

    private val _openActionView: Channel<ActionResultPresentation?> = Channel()
    val openActionView get() = _openActionView.receiveAsFlow()

    protected fun setActionByResult(actionStatus: Any? = null, error: ActionResultMessage? = null) {
        launch {
            _openActionView.send(
                ActionResultPresentation(
                    actionStatus = actionStatus,
                    errorMessage = error
                )
            )
        }
    }

    protected fun showError(error: Exception) {
        log("error data $error")
        when (error) {
            is Failure.Generic -> setActionByResult(
                error = ActionResultMessage.ResourceMessage(R.string.generic_error)
            )
            is Failure.NoInternet -> setActionByResult(
                error = ActionResultMessage.ResourceMessage(R.string.internet_error)
            )
            is Failure.Source -> setActionByResult(
                error = ActionResultMessage.StringMessage(
                    error.errorMessage ?: error.additionalMessage.orEmpty()
                )
            )
            else -> setActionByResult(error.message)
        }
    }

    fun onCloseActionByResult() {
        launch {
            _openActionView.send(null)
        }
    }

    protected fun onActionDelegate(action: ActionResultPresentation?) {
        launch { _openActionView.send(action) }
    }

    protected fun onErrorDelegate(error: Exception) {
        debounceError(error)
    }

    private val debounceError = throttleFirst<Exception>(
        DELAY_500L,
        coroutineScope = viewModelScope
    ) { showError(it) }

    /** Init setup delegate**/
    open fun onSetupDelegate() {}

    /** Function action from other argument **/
    open fun onListenerActionFromOtherScreen() {}
}
