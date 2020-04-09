package com.pragati.karuna.request.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class Supplier(
    var name: String,
    var locality: String,
    var street_name: String,
    var landmark: String,
    var city: String,
    var state: String,
    var pincode: String,
    var mobile_number: String,
    var bank_account: String,
    var ifsc: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
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
        parcel.writeString(locality)
        parcel.writeString(street_name)
        parcel.writeString(landmark)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(pincode)
        parcel.writeString(mobile_number)
        parcel.writeString(bank_account)
        parcel.writeString(ifsc)
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
        return locality + street_name + landmark + city + state + pincode
    }
}