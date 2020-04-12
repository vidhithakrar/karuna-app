package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class Supplier(
    var name: String,
    var id: String,
    var locality: String?,
    var city: String,
    var state: String?,
    var supplier_type: String,
    var mobile_number: String?
) : Parcelable {
    fun getAddress(): String {
        return "$locality, $city, $state."
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Supplier

        if (name != other.name) return false
        if (id != other.id) return false
        if (locality != other.locality) return false
        if (city != other.city) return false
        if (state != other.state) return false
        if (supplier_type != other.supplier_type) return false
        if (mobile_number != other.mobile_number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (locality?.hashCode() ?: 0)
        result = 31 * result + city.hashCode()
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + supplier_type.hashCode()
        result = 31 * result + (mobile_number?.hashCode() ?: 0)
        return result
    }
}