package com.pragati.karuna.login.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Keep
@Parcelize
data class LoggedInUser(
    val userId: String,
    val email: String
): Parcelable
