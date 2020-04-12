package com.pragati.karuna.request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Family

class FamilyDetailsAdapter(var myRequests: List<Family>, var onFamilyItemClickListener: OnFamilyItemClickListener) :
    RecyclerView.Adapter<FamilyDetailsAdapter.MyRequestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRequestsViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_family_details, parent, false)
        return MyRequestsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myRequests.count()
    }

    override fun onBindViewHolder(holder: MyRequestsViewHolder, position: Int) {
        val family = myRequests.get(position)
        holder.title.text = family.familyLeader
        val count = family.noOfAdults + family.noOfChildren
        holder.subTitle.text = "$count members"
    }

    companion object
    class MyRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var subTitle: TextView = itemView.findViewById(R.id.tv_sub_title)
    }
}

interface OnFamilyItemClickListener {
    fun onDeleteClick(position: Int)
    fun onEditClick(position: Int)
}