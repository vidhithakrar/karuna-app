package com.pragati.karuna.request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.util.gone
import com.pragati.karuna.util.invisible
import com.pragati.karuna.util.visible

class FamilyDetailsAdapter(private var onFamilyItemClickListener: OnFamilyItemClickListener) :
    RecyclerView.Adapter<FamilyDetailsAdapter.MyRequestsViewHolder>() {

    private val families: ArrayList<Family> = arrayListOf()

    fun updateData(familyList: List<Family>) {
        this.families.clear()
        this.families.addAll(familyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRequestsViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_family_details, parent, false)
        return MyRequestsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return families.count()
    }

    override fun onBindViewHolder(holder: MyRequestsViewHolder, position: Int) {
        val family = families[position]
        holder.title.text = family.familyLeader
        val count = family.noOfAdults + family.noOfChildren
        holder.subTitle.text = "$count members"
        holder.deleteFamily.setOnClickListener {
            onFamilyItemClickListener.onDeleteClick(position)
        }
        holder.editFamily.setOnClickListener {
            onFamilyItemClickListener.onEditClick(position)
        }

        if(family.isFlagged()) {
            holder.servedDaysAgoView.text = holder.itemView.context.resources.getQuantityString(
                R.plurals.served_days_ago,
                family.lastServedBeforeDays(),
                family.lastServedBeforeDays()
            )
            holder.servedDaysAgoView.visible()
        } else {
            holder.servedDaysAgoView.gone()
        }
    }

    companion object
    class MyRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val subTitle: TextView = itemView.findViewById(R.id.tv_sub_title)
        val deleteFamily: ImageView = itemView.findViewById(R.id.delete_family)
        val editFamily: ImageView = itemView.findViewById(R.id.edit_family)
        val servedDaysAgoView: TextView = itemView.findViewById(R.id.tv_served_days_ago)
    }
}

interface OnFamilyItemClickListener {
    fun onDeleteClick(position: Int)
    fun onEditClick(position: Int)
}