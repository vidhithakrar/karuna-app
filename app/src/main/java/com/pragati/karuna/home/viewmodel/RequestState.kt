package com.pragati.karuna.home.viewmodel

import androidx.annotation.IntDef

data class RequestState(
    @State
    val state: Int,
    val message: Int
) {
    companion object {

        @Retention(AnnotationRetention.SOURCE)
        @IntDef(CREATED, UPDATED, CLOSED, FAILED)
        annotation class State

        const val CREATED = 0
        const val UPDATED = 1
        const val CLOSED = 2
        const val FAILED = 3
    }
}



