package com.pragati.karuna.core.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pragati.karuna.R
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
        actionButton.text = expandedActionName.orEmpty()
        actionButton.setLeftDrawable(actionDrawable)
        contentGroup.visibleIf(isContentVisible)
        bindCollapsedState()
        attributes.recycle()
    }

    fun bindExpandedState() {
        collapsedLayoutState.gone()
        expandLayoutState.visible()
    }

    fun bindCollapsedState() {
        collapsedLayoutState.visible()
        expandLayoutState.gone()
    }
}