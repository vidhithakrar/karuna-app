package com.pragati.karuna.ui.kit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.phelat.navigationresult.BundleFragment
import com.phelat.navigationresult.navigateUp
import com.pragati.karuna.R
import com.pragati.karuna.models.Kit
import kotlinx.android.synthetic.main.fragment_add_kit.*
import kotlinx.android.synthetic.main.fragment_add_location.btn_next

class AddKitFragment : BundleFragment() {

    private lateinit var kitViewModel: KitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kitViewModel =
            ViewModelProviders.of(this).get(KitViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_kit, container, false)
        kitViewModel.kit.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener(View.OnClickListener {
            var kit = Kit(sp_kit_type.selectedItem.toString(), et_kit_description.text.toString())
            var bundle = bundleOf("kit" to kit)
            navigateUp(2, bundle)
        })
    }
}
