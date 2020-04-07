package com.pragati.karuna.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class Location(
    var address: String? = null,
    var landmark: String = "",
    var pin: String? = null,
    var contactName: String? = null,
    var phone: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(landmark)
        parcel.writeString(pin)
        parcel.writeString(contactName)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}