package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.request.adapter.SupplierAdapter
import com.pragati.karuna.request.viewmodel.SuppliersViewModel
import kotlinx.android.synthetic.main.fragment_add_suppliers.*

class AddSuppliersFragment : BundleFragment() {

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

        suppliers_list_container?.layoutManager = LinearLayoutManager(context)
        suppliers_list_container?.adapter = SupplierAdapter(suppliersViewModel)

        suppliersViewModel.fetchSuppliers()
        // pass callback to refresh the collection view
    }
}
