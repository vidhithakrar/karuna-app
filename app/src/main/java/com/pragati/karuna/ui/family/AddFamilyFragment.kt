package com.pragati.karuna.ui.family

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
import com.pragati.karuna.models.Family
import com.pragati.karuna.viewmodel.FamilyViewModel
import kotlinx.android.synthetic.main.fragment_add_family.*
import kotlinx.android.synthetic.main.fragment_add_location.btn_next

class AddFamilyFragment : BundleFragment() {

    private lateinit var familyViewModel: FamilyViewModel

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
        btn_next.setOnClickListener(View.OnClickListener {
            var family = Family(
                et_family_leader.text.toString(),
                et_contact_number.text.toString(),
                if (!et_no_of_members.text.toString().isEmpty()) et_no_of_members.text.toString().toInt() else 0,
                if (!et_no_of_kits.text.toString().isEmpty()) et_no_of_kits.text.toString().toInt() else 0
            )

            var bundle = bundleOf("family" to family)
            navigateUp(1, bundle)
        })
    }
}
