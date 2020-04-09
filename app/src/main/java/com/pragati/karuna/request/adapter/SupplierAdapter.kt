package com.pragati.karuna.request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.viewmodel.SuppliersViewModel

class SupplierAdapter(private val viewModel: SuppliersViewModel) : RecyclerView.Adapter<SupplierAdapter.SupplierCell>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierCell {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.supplier_details_cell, parent, false)
        return SupplierCell(
            view
        )
    }

    override fun getItemCount(): Int {
        return viewModel.suppliers.count()
    }

    override fun onBindViewHolder(holder: SupplierCell, position: Int) {
        holder.name.text = viewModel.suppliers[position].name
        holder.address.text = viewModel.suppliers[position].getAddress()
    }

     companion object class SupplierCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.supplier_name)
        var address: TextView = itemView.findViewById(R.id.supplier_address)
    }
}

