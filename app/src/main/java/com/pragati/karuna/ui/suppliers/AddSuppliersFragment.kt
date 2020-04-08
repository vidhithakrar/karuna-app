package com.pragati.karuna.ui.suppliers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.viewmodel.SuppliersViewModel
import kotlinx.android.synthetic.main.fragment_add_suppliers.*

class AddSuppliersFragment : BundleFragment() {

    private lateinit var suppliersViewModel: SuppliersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        suppliersViewModel =
            ViewModelProviders.of(this).get(SuppliersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_suppliers, container, false)
        suppliersViewModel.suppliers.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        suppliers_list_container.layoutManager = LinearLayoutManager(context)
        val list = listOf("ketan", "someone", "someotherone")
        suppliers_list_container.adapter = SupplierAdapter(list)
    }
}
