package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class Volunteer(
    var name: String,
    var id: String,
    var locality: String?,
    var city: String?,
    var state: String?,
    var mobile_number: Long?
) : Parcelable {
    fun getAddress(): String {
        return "$locality, $city, $state."
    }
}