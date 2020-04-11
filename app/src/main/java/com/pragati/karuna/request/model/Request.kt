package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Request(
    var requestId: String? = null,
    var location: Location = Location(),
    var families: MutableList<Family> = mutableListOf(),
    var kit: Kit = Kit(),
    var supplierId: String = "",
    var volunteerId: String = "",
    val createdTimestamp: Long = 0,
    val modifiedTimestamp: Long = 0
) : Parcelable