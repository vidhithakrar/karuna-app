package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class Location(
    var address: String? = null,
    var landmark: String = "",
    var pin: String? = null,
    var contactName: String? = null,
    var phone: String = ""
) : Parcelable