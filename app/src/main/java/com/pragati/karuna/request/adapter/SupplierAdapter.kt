package com.pragati.karuna.request.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pragati.karuna.R
import com.pragati.karuna.request.viewmodel.SuppliersViewModel
import kotlinx.android.synthetic.main.supplier_details_cell.view.*

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

        // tag, un-tag suppliers
        holder.checkBox.tag = position
        holder.checkBox.setOnClickListener {
            val checkedPosition = holder.checkBox.tag as Int
            if (holder.checkBox.isChecked) {
                // supplier tagged
                viewModel.taggedSuppliers.add(viewModel.suppliers[checkedPosition])
            } else {
                // supplier untagged
                viewModel.taggedSuppliers.remove(viewModel.suppliers[checkedPosition])
            }
        }

        // call suppliers
        holder.makePhoneCall.tag = position
        holder.makePhoneCall.setOnClickListener {
            val tappedPosition = holder.makePhoneCall.tag as Int
            val phoneNumber = viewModel.suppliers[tappedPosition].mobile_number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(phoneNumber)
//            launch phone screen on tap of phone icon
//            startActivity(context, intent, bundle)
        }
    }

     companion object class SupplierCell(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.supplier_name
        var address: TextView = itemView.supplier_address
        var checkBox: CheckBox = itemView.supplier_checkBox
        var makePhoneCall: ImageButton = itemView.supplier_call
    }
}

