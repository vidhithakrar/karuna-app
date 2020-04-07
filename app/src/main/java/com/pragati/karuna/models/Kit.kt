package com.pragati.karuna.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class Kit(var type: String = "", var description: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kit> {
        override fun createFromParcel(parcel: Parcel): Kit {
            return Kit(parcel)
        }

        override fun newArray(size: Int): Array<Kit?> {
            return arrayOfNulls(size)
        }
    }
}
