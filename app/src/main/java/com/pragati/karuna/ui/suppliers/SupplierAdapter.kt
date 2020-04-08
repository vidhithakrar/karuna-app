package com.pragati.karuna.ui.suppliers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R

class SupplierAdapter(private val suppliers: List<String>) : RecyclerView.Adapter<SupplierAdapter.SupplierCell>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierCell {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.supplier_details_cell, parent, false)
        return SupplierCell(view)
    }

    override fun getItemCount(): Int {
        return suppliers.count()
    }

    override fun onBindViewHolder(holder: SupplierCell, position: Int) {
        holder.name.text = suppliers[position]
        holder.address.text = "address - $suppliers[position]"
    }

     companion object class SupplierCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.supplier_name)
        var address: TextView = itemView.findViewById(R.id.supplier_address)
    }
}

