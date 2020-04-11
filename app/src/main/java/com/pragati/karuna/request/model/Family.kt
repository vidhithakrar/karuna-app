package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class Family(
    var familyLeader: String? = "",
    var contact: String = "",
    var noOfAdults: Int = 0,
    var noOfChildren: Int = 0,
    var noOfKits: Int = 0
) : Parcelable