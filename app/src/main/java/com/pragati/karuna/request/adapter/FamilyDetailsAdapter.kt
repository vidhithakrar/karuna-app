package com.pragati.karuna.request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Family

class FamilyDetailsAdapter(var onFamilyItemClickListener: OnFamilyItemClickListener) :
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
        val family = families.get(position)
        holder.title.text = family.familyLeader
        val count = family.noOfAdults + family.noOfChildren
        holder.subTitle.text = "$count members"
        holder.deleteFamily.setOnClickListener {
            onFamilyItemClickListener.onDeleteClick(position)
        }
    }

    companion object
    class MyRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var subTitle: TextView = itemView.findViewById(R.id.tv_sub_title)
        var deleteFamily: ImageView = itemView.findViewById(R.id.delete_family)
    }
}

interface OnFamilyItemClickListener {
    fun onDeleteClick(position: Int)
    fun onEditClick(position: Int)
}