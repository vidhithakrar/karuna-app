package com.pragati.karuna.request.model

import androidx.annotation.Keep

@Keep
data class Request(
    var location: Location = Location(),
    var families: MutableList<Family> = mutableListOf(),
    var kit: Kit = Kit()
)