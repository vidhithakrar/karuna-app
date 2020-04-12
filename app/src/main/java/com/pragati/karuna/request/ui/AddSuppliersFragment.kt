package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.phelat.navigationresult.navigateUp
import com.pragati.karuna.R
import com.pragati.karuna.request.adapter.SupplierAdapter
import com.pragati.karuna.request.viewmodel.SuppliersViewModel
import com.pragati.karuna.util.disable
import com.pragati.karuna.util.enable
import kotlinx.android.synthetic.main.fragment_add_suppliers.*

class AddSuppliersFragment : BundleFragment() {

    companion object {
        const val SUPPLIER = "supplier"
    }

    private lateinit var suppliersViewModel: SuppliersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        suppliersViewModel = ViewModelProviders.of(this).get(SuppliersViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_suppliers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirm_suppliers_button?.disable()
        suppliers_list_container?.layoutManager = LinearLayoutManager(context)
        suppliers_list_container?.adapter = SupplierAdapter(context!!, object: OnSupplierSelected {
            override fun onSelected() {
                confirm_suppliers_button.enable()
            }

        })

        confirm_suppliers_button?.setOnClickListener(View.OnClickListener {
            val taggedSupplier = (suppliers_list_container?.adapter as SupplierAdapter).getTaggedSuppliers()[0]
            val bundle = bundleOf(SUPPLIER to taggedSupplier)
            navigateUp(3, bundle)
        })

        suppliersViewModel.suppliers.observe(viewLifecycleOwner, Observer {
            (suppliers_list_container?.adapter as SupplierAdapter).setSuppliers(suppliersViewModel.suppliers.value!!)
        })

        suppliersViewModel.fetchSuppliers()
    }

    interface OnSupplierSelected {
        fun onSelected()
    }
}
