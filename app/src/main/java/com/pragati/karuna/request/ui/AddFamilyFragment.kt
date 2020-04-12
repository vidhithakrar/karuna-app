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
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.ui.AddFamilyFragment.ScreenType.FAMILY_DETAILS
import com.pragati.karuna.request.ui.AddFamilyFragment.ScreenType.HOME
import com.pragati.karuna.request.viewmodel.FamilyViewModel
import com.pragati.karuna.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_add_family.*

class AddFamilyFragment : BundleFragment() {

    private lateinit var familyViewModel: FamilyViewModel
    private var fromScreen = HOME
    private var position = -1
    private var family: Family? = null

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
        showKeyboard()
        family = arguments?.getParcelable("defaultFamilyValues")
        fromScreen = arguments?.getInt("fromScreen") ?: HOME
        position = arguments?.getInt("position") ?: -1
        setEditTextValue()

        btn_save.setOnClickListener(View.OnClickListener {
            if (!validatePhone()) {
                et_contact_number.requestFocus()
            } else if (!validateNoOfKits()) {
                et_no_of_kits.requestFocus()
            } else {
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

    private fun validatePhone(): Boolean {
        val contactNumber = et_contact_number.text.toString()
        var isValidPhone = false
        if (contactNumber.isNullOrBlank()) {
            et_contact_number.error = getString(R.string.contact_no_empty_error)
        } else if (contactNumber.length != 10) {
            et_contact_number.error = getString(R.string.invalid_contact_no_error)
        } else {
            et_contact_number.error = null
            isValidPhone = true
        }

        return isValidPhone
    }

    private fun validateNoOfKits(): Boolean {
        val noOfKits = et_no_of_kits.text.toString()
        var hasNoOfKits = false
        if (noOfKits.isNullOrBlank()) {
            et_no_of_kits.error = getString(R.string.no_of_kits_error)
        } else if (noOfKits.toInt() <= 0) {
            et_no_of_kits.error = getString(R.string.no_of_kits_error)
        } else {
            et_no_of_kits.error = null
            hasNoOfKits = true
        }

        return hasNoOfKits
    }

    object ScreenType {
        const val HOME = 1
        const val FAMILY_DETAILS = 2
    }
}
