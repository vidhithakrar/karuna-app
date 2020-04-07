package com.pragati.karuna.ui.suppliers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R

class AddSuppliersFragment : BundleFragment() {

    private lateinit var suppliersViewModel: SuppliersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        supperliers_list_container.layoutManager = LinearLayoutManager(context);
//        supperliers_list_container.adapter = SupplierAdaptor

        suppliersViewModel =
            ViewModelProviders.of(this).get(SuppliersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_suppliers, container, false)
        suppliersViewModel.suppliers.observe(viewLifecycleOwner, Observer {

        })

        return root
    }
}
