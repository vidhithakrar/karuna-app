package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class Kit(var type: String = "", var description: String? = null) : Parcelable