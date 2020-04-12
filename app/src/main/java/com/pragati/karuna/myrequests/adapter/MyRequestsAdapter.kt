package com.pragati.karuna.myrequests.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Request
import java.text.SimpleDateFormat
import java.util.*


class MyRequestsAdapter(var myRequests: List<Request>, var itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MyRequestsAdapter.MyRequestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRequestsViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_request, parent, false)
        return MyRequestsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myRequests.count()
    }

    override fun onBindViewHolder(holder: MyRequestsViewHolder, position: Int) {
        holder.title.text = myRequests.get(position).location.address
        holder.subTitle.text = myRequests.get(position).location.landmark
        holder.accessory.text = holder.itemView.context.resources.getQuantityString(
            R.plurals.no_of_families,
            myRequests.get(position).families.count(), myRequests.get(position).families.count()
        )
        
        holder.hint.text = holder.itemView.context.resources.getQuantityString(
            R.plurals.no_of_kits,
            myRequests.get(position).numberOfKits, myRequests.get(position).numberOfKits
        )
        holder.subAccessory.text = dateFromTimeStamp(myRequests.get(position).createdTimestamp)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    private fun dateFromTimeStamp(timeStamp: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy '|' HH:mm")
        dateFormat.setTimeZone(TimeZone.getDefault())
        return dateFormat.format(timeStamp)
    }


    companion object
    class MyRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var subTitle: TextView = itemView.findViewById(R.id.tv_sub_title)
        var accessory: TextView = itemView.findViewById(R.id.tv_accessory)
        var subAccessory: TextView = itemView.findViewById(R.id.tv_sub_accessory)
        var hint: TextView = itemView.findViewById(R.id.tv_hint)

    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}