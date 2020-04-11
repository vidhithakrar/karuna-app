package com.pragati.karuna.home.viewmodel

data class RequestState(
    val created: Boolean = false,
    val closed: Boolean = false,
    val failure: Int? = null
)
