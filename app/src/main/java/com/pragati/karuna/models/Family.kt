package com.pragati.karuna.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class Family(
    var familyLeader: String?,
    var contact: String,
    var noOfAdults: Int = 0,
    var noOfChildren: Int = 0,
    var noOfKits: Int = 0
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(familyLeader)
        parcel.writeString(contact)
        parcel.writeInt(noOfAdults)
        parcel.writeInt(noOfChildren)
        parcel.writeInt(noOfKits)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Family> {
        override fun createFromParcel(parcel: Parcel): Family {
            return Family(parcel)
        }

        override fun newArray(size: Int): Array<Family?> {
            return arrayOfNulls(size)
        }
    }
}