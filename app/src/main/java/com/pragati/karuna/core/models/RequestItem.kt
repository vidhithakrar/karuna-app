package com.pragati.karuna.core.models

import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.model.Location
import com.pragati.karuna.request.model.Supplier

sealed class RequestItem {
    data class LocationItem(val location: Location): RequestItem()
    data class KitItem(val kit: Kit): RequestItem()
    data class FamilyItem(val families: List<Family>): RequestItem()
    data class SupplierItem(val supplier: Supplier): RequestItem()
}