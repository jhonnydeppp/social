package com.jhonny.social.presenter.base.entities

import kotlin.random.Random

data class ActionResultPresentation(
    val actionStatus: Any? = null,
    val errorMessage: ActionResultMessage? = null,
    val random: Int = Random.nextInt()
)
