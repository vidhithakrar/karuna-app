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
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.ui.AddFamilyFragment.ScreenType.FAMILY_DETAILS
import com.pragati.karuna.request.ui.AddFamilyFragment.ScreenType.HOME
import com.pragati.karuna.request.viewmodel.FamilyViewModel
import kotlinx.android.synthetic.main.fragment_add_family.*

class AddFamilyFragment : BundleFragment() {

    private lateinit var familyViewModel: FamilyViewModel
    private var fromScreen = HOME
    private var position = -1
    private var family: Family?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        familyViewModel =
            ViewModelProviders.of(this).get(FamilyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_family, container, false)
        familyViewModel.family.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        family = arguments?.getParcelable("defaultFamilyValues")
        fromScreen = arguments?.getInt("fromScreen")?: HOME
        position = arguments?.getInt("position")?: -1
        setEditTextValue()
        et_contact_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var contactNumber = s.toString()
                btn_save.isEnabled = contactNumber.length == 10
                validatePhone(contactNumber)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePhone(et_contact_number.text.toString())
            }
        })

        et_contact_number.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                false -> {
                    validatePhone(et_contact_number.text.toString())
                }
            }
        }

        btn_save.setOnClickListener(View.OnClickListener {
            val family = Family(
                et_family_leader.text.toString(),
                et_contact_number.text.toString(),
                if (!et_no_of_adults.text.toString().isEmpty()) et_no_of_adults.text.toString().toInt() else 0,
                if (!et_no_of_children.text.toString().isEmpty()) et_no_of_children.text.toString().toInt() else 0,
                if (!et_no_of_kits.text.toString().isEmpty()) et_no_of_kits.text.toString().toInt() else 0
            )

            if (fromScreen == FAMILY_DETAILS) {
                val bundle = bundleOf("family" to family, "position" to position)
                navigateUp(6, bundle)
            } else {
                val bundle = bundleOf("family" to family)
                navigateUp(1, bundle)
            }
        })
    }

    private fun setEditTextValue() {
        family?.let {
            et_contact_number.setText(it.contact)
            et_family_leader.setText(it.familyLeader)
            et_no_of_adults.setText(it.noOfAdults.toString())
            et_no_of_children.setText(it.noOfChildren.toString())
            et_no_of_kits.setText(it.noOfKits.toString())
        }
    }

    private fun validatePhone(contactNumber: String?) {
        if (contactNumber.isNullOrBlank())
            et_contact_number.error = "Contact Number is mandatory"
        else if (contactNumber.length != 10)
            et_contact_number.error = "Contact Number is not valid"
        else
            et_contact_number.error = null
    }

    object ScreenType {
        const val HOME = 1
        const val FAMILY_DETAILS = 2
    }
}
