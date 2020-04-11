package com.pragati.karuna.request.repository

import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.model.Location

data class RequestDao(
    val location: Location,
    val families: MutableList<Family>,
    val kit: Kit,
    val uid: String,
    val status: Status
)