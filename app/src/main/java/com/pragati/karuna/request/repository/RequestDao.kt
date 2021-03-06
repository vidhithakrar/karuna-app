package com.pragati.karuna.request.repository

import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.model.Location

data class RequestDao(
    val location: Location,
    val kit: Kit,
    val numberOfKits: Int,
    val supplierId: String?,
    val volunteerId: String?,
    val uid: String,
    val status: Status,
    val createdTimestamp: Long,
    val modifiedTimestamp: Long
) {
    //Only needed by firebase to de-serialize this data
    constructor() : this(Location(), Kit(), 0, null, null, "", Status.CREATED, 0, 0)
}

