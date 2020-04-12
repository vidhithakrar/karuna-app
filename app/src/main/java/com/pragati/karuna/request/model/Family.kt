package com.pragati.karuna.request.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.util.*
import java.util.concurrent.TimeUnit

private const val SECONDS_IN_DAY = 86400
private const val THRESHOLD_DAYS_FOR_FAGGING = 3

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
        val unixTimeStampToday = System.currentTimeMillis() / 1000L
        val timeSinceLastServed = unixTimeStampToday - lastServedDate
        return (timeSinceLastServed / SECONDS_IN_DAY) < THRESHOLD_DAYS_FOR_FAGGING
    }

    fun lastServedBeforeDays(): Int {
        val unixTimeStampToday = System.currentTimeMillis() / 1000L
        val timeSinceLastServed = unixTimeStampToday - lastServedDate
        return (timeSinceLastServed / SECONDS_IN_DAY).toInt()
    }
}