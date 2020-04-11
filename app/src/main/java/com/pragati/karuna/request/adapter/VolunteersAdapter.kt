package com.pragati.karuna.request.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Volunteer
import com.pragati.karuna.request.ui.AddSuppliersFragment

class VolunteersAdapter(
    val context: Context,
    val supplierSelectedListener: AddSuppliersFragment.OnSupplierSelected
) : RecyclerView.Adapter<SupplierAdapter.SupplierCell>() {

    private var volunteers: List<Volunteer> = listOf()
    private var taggedVolunteers: MutableList<Volunteer> = mutableListOf()

    fun getTaggedVolunteers(): MutableList<Volunteer> {
        return taggedVolunteers
    }

    fun setVolunteers(volunteers: List<Volunteer>){
        this.volunteers = volunteers
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierAdapter.SupplierCell {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.supplier_details_cell, parent, false)
        val holder = SupplierAdapter.SupplierCell(view)
        holder.checkBox.setOnClickListener {
            val checkedPosition = holder.adapterPosition

            if(taggedVolunteers.isEmpty()) {
                supplierSelectedListener.onSelected()
            }

            taggedVolunteers.clear()
            taggedVolunteers.add(volunteers[checkedPosition])

            //Todo: Use notifyItemChanged instead
            notifyDataSetChanged()
        }

        holder.makePhoneCall.setOnClickListener {
            val phoneNumber = volunteers[holder.adapterPosition].mobile_number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(holder.itemView.context, intent, bundleOf())
        }

        return holder
    }

    override fun getItemCount(): Int {
        return volunteers.count()
    }

    override fun onBindViewHolder(holder: SupplierAdapter.SupplierCell, position: Int) {
        holder.name.text = volunteers[position].name
        holder.address.text = volunteers[position].getAddress()
        holder.checkBox.isChecked = taggedVolunteers.contains(volunteers[position])
    }
}

