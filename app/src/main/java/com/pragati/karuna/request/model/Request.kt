package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Request(
    var requestId: String? = null,
    val location: Location = Location(),
    val families: MutableList<Family> = mutableListOf(),
    val kit: Kit = Kit(),
    val supplierId: String? = null,
    val volunteerId: String? = null,
    val createdTimestamp: Long = 0,
    val modifiedTimestamp: Long = 0
) : Parcelable