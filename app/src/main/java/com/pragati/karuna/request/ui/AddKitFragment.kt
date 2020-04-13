package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phelat.navigationresult.BundleFragment
import com.phelat.navigationresult.navigateUp
import com.pragati.karuna.R
import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.viewmodel.KitViewModel
import com.pragati.karuna.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_add_kit.*
import java.util.*


class AddKitFragment : BundleFragment() {

    private lateinit var kitViewModel: KitViewModel
    private var kit: Kit? = null

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
        kit = arguments?.getParcelable("defaultKitValues")
        setEditTextValue()
        showKeyboard()
        btn_save.setOnClickListener(View.OnClickListener {
            var kit = Kit(sp_kit_type.selectedItem.toString(), et_kit_description.text.toString())
            var bundle = bundleOf("kit" to kit)
            navigateUp(2, bundle)
        })
    }

    private fun setEditTextValue() {
        kit?.let {
            val kitTypes = resources.getStringArray(R.array.kit_types)
            sp_kit_type.setSelection(kitTypes.indexOf(it.type))
            et_kit_description.setText(it.description)
        }
    }
}
