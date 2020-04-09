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
import com.pragati.karuna.request.model.Location
import com.pragati.karuna.request.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.fragment_add_location.*

class AddLocationFragment : BundleFragment() {

    private lateinit var addLocationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addLocationViewModel =
            ViewModelProviders.of(this).get(LocationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_location, container, false)
        addLocationViewModel.location.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_next.setOnClickListener(View.OnClickListener {
            var location = Location(
                et_address.text.toString(),
                et_landmark.text.toString(),
                et_pin.text.toString(),
                et_contact_name.text.toString(),
                et_contact_phone.text.toString()
            )

            var bundle = bundleOf("location" to location)
            navigateUp(0, bundle)
        })
    }
}
