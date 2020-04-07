package com.pragati.karuna.models

data class Request(
    var location: Location = Location(),
    var families: MutableList<Family> = mutableListOf(),
    var kit: Kit = Kit()
)