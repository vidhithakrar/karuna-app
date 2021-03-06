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
import com.pragati.karuna.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_add_location.*

class AddLocationFragment : BundleFragment() {

    private lateinit var addLocationViewModel: LocationViewModel
    private var location: Location ?= null

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
        location = arguments?.getParcelable("defaultLocationValues")
        setEditTextValue()
        showKeyboard()
        btn_save.setOnClickListener(View.OnClickListener {
            if (!validateAddress()) {
                et_address.requestFocus()
            } else if (!validateLandmark()) {
                et_landmark.requestFocus()
            } else {
                var location = Location(
                    et_address.text.toString(),
                    et_landmark.text.toString(),
                    et_pin.text.toString(),
                    et_contact_name.text.toString(),
                    et_contact_phone.text.toString()
                )

                var bundle = bundleOf("location" to location)
                navigateUp(0, bundle)
            }
        })
    }

    private fun setEditTextValue() {
        location?.let {
            et_address.setText(it.address)
            et_landmark.setText(it.landmark)
            et_pin.setText(it.pin)
            et_contact_name.setText(it.contactName)
            et_contact_phone.setText(it.phone)
        }
    }

    private fun validateLandmark(): Boolean {
        val landMark: String? = et_landmark.text.toString()
        var isValidLandmark = !landMark.isNullOrBlank()
        et_landmark.error =
            if (isValidLandmark) null else resources.getString(R.string.landmark_error)

        return isValidLandmark
    }

    private fun validateAddress(): Boolean {
        val address = et_address.text.toString()
        var isValidAddress = !address.isNullOrBlank()
        et_address.error = if (isValidAddress) null else resources.getString(R.string.address_error)

        return isValidAddress
    }
}
