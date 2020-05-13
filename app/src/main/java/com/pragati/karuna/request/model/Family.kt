package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

private const val MILLIS_IN_DAY = 86400 * 1000
private const val THRESHOLD_DAYS_FOR_FLAGGING = 10

@Keep
@Parcelize
class Family(
    var familyLeader: String? = "",
    var contact: String = "",
    var noOfAdults: Int = 0,
    var noOfChildren: Int = 0,
    var noOfKits: Int = 0,
    var lastServedDate: Long = 0
) : Parcelable {

    fun isFlagged(): Boolean {
        val unixTimeStampToday = System.currentTimeMillis()
        val timeSinceLastServed = unixTimeStampToday - lastServedDate
        return (timeSinceLastServed / MILLIS_IN_DAY) < THRESHOLD_DAYS_FOR_FLAGGING
    }

    fun lastServedBeforeDays(): Int {
        val unixTimeStampToday = System.currentTimeMillis()
        val timeSinceLastServed = unixTimeStampToday - lastServedDate
        return (timeSinceLastServed / MILLIS_IN_DAY).toInt()
    }
}