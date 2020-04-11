package com.pragati.karuna.core.models

import com.pragati.karuna.request.model.*

sealed class RequestItem {
    data class LocationItem(val location: Location): RequestItem()
    data class KitItem(val kit: Kit): RequestItem()
    data class FamilyItem(val families: List<Family>): RequestItem()
    data class SupplierItem(val supplier: Supplier): RequestItem()
    data class VolunteerItem(val volunteer: Volunteer): RequestItem()
}