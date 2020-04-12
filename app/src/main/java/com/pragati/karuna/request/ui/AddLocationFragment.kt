package com.pragati.karuna.request.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private var isValidAddress = false
    private var isValidLandmark = false

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
        showKeyboard()
        et_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var contactNumber = s.toString()
                validateAddress(contactNumber)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateAddress(et_address.text.toString())
            }
        })

        et_landmark.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var contactNumber = s.toString()
                validateLandmark(contactNumber)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateLandmark(et_landmark.text.toString())
            }
        })

        et_landmark.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                false -> {
                    validateLandmark(et_landmark.text.toString())
                }
            }
        }

        et_address.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                false -> {
                    validateAddress(et_address.text.toString())
                }
            }
        }

        btn_save.setOnClickListener(View.OnClickListener {
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

    private fun validateLandmark(contactNumber: String?) {
        isValidLandmark = false
        if (contactNumber.isNullOrBlank())
            et_landmark.error = resources.getString(R.string.landmark_error)
        else {
            et_landmark.error = null
            isValidLandmark = true
        }
        btn_save.isEnabled = isValidAddress and isValidLandmark
    }

    private fun validateAddress(address: String?) {
        isValidAddress = false
        if (address.isNullOrBlank())
            et_address.error = resources.getString(R.string.address_error)
        else {
            et_address.error = null
            isValidAddress = true
        }
        btn_save.isEnabled = isValidAddress and isValidLandmark
    }
}
