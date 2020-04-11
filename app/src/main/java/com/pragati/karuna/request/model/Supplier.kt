package com.pragati.karuna.request.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class Supplier(
    var name: String,
    var id: String,
    var locality: String,
    var city: String,
    var state: String,
    var supplier_type: String,
    var mobile_number: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeString(locality)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(supplier_type)
        parcel.writeString(mobile_number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Supplier> {
        override fun createFromParcel(parcel: Parcel): Supplier {
            return Supplier(parcel)
        }

        override fun newArray(size: Int): Array<Supplier?> {
            return arrayOfNulls(size)
        }
    }

    fun getAddress(): String {
        return "$locality, $city, $state."
    }
}