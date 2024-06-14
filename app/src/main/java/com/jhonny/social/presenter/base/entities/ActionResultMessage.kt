package com.jhonny.social.presenter.base.entities

sealed class ActionResultMessage {
    data class ResourceMessageWithParam(val message: Int, val param: String) : ActionResultMessage()
    data class StringMessage(val message: String) : ActionResultMessage()
    data class ResourceMessage(val message: Int) : ActionResultMessage()
}
