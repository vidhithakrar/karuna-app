package com.pragati.karuna.core.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pragati.karuna.R
import com.pragati.karuna.core.models.RequestItem
import com.pragati.karuna.request.model.*
import com.pragati.karuna.util.gone
import com.pragati.karuna.util.setLeftDrawable
import com.pragati.karuna.util.visible
import com.pragati.karuna.util.visibleIf
import kotlinx.android.synthetic.main.view_request_summary.view.*
import kotlinx.android.synthetic.main.view_request_summary_collapse.view.*
import kotlinx.android.synthetic.main.view_request_summary_expand.view.*

class RequestItemView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.view_request_summary, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.request_item_view)
        val collapsedName = attributes.getString(R.styleable.request_item_view_collapsedName)
        val expandedName = attributes.getString(R.styleable.request_item_view_expandedName)
        val expandedActionName = attributes.getString(R.styleable.request_item_view_expendedActionName)
        val actionDrawable = attributes.getDrawable(R.styleable.request_item_view_expandedActionDrawable)
        val isContentVisible = attributes.getBoolean(R.styleable.request_item_view_contentVisible, true)
        nameExpandState.text = expandedName.orEmpty()
        nameCollapsedState.text = collapsedName.orEmpty()
        editDetails.text = expandedActionName.orEmpty()
        editDetails.setLeftDrawable(actionDrawable)
        contentGroup.visibleIf(isContentVisible)
        bindCollapsedState()
        attributes.recycle()
    }

    fun bindExpandedState(requestItem: RequestItem) {
        collapsedLayoutState.gone()
        expandLayoutState.visible()
        when(requestItem) {
             is RequestItem.LocationItem -> { bindLocationDetails(requestItem.location)}
             is RequestItem.KitItem -> { bindKitDetails(requestItem.kit) }
             is RequestItem.FamilyItem -> { bindFamilyDetails(requestItem.families) }
             is RequestItem.SupplierItem -> { bindSupplierDetails(requestItem.supplier) }
             is RequestItem.VolunteerItem -> { bindVolunteerDetails(requestItem.volunteer) }
        }
    }

    private fun bindSupplierDetails(supplier: Supplier) {
        content1.text = supplier.name
        content2.text = supplier.getAddress()
    }

    private fun bindVolunteerDetails(volunteer: Volunteer) {
        content1.text = volunteer.name
        content2.text = volunteer.getAddress()
    }

    private fun bindFamilyDetails(families: List<Family>) {
        nameExpandState.text = context.resources.getQuantityString(R.plurals.no_of_families, families.size, families.size)
    }

    private fun bindKitDetails(kit: Kit) {
        content1.text = kit.type
        content2.text = kit.description
    }

    private fun bindLocationDetails(location: Location) {
        content1.text = location.address
        content2.text = location.pin
    }

    fun bindCollapsedState() {
        collapsedLayoutState.visible()
        expandLayoutState.gone()
    }
}