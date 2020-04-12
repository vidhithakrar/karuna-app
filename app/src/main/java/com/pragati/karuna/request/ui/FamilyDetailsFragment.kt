package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.phelat.navigationresult.navigateUp
import com.pragati.karuna.R
import com.pragati.karuna.myrequests.adapter.VerticalSpaceItemDecoration
import com.pragati.karuna.request.adapter.FamilyDetailsAdapter
import com.pragati.karuna.request.adapter.OnFamilyItemClickListener
import com.pragati.karuna.request.model.*
import kotlinx.android.synthetic.main.fragment_family_details.*
import java.util.ArrayList

class FamilyDetailsFragment : BundleFragment(), OnFamilyItemClickListener {

    val families = arrayListOf<Family>()
    val familyAdapter = FamilyDetailsAdapter(this@FamilyDetailsFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_family_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelableArrayList<Family>("families")?.let { familyList ->
            families.clear()
            families.addAll(familyList)
            setRequestAdapter(families)
        }
        confirmButton.setOnClickListener {
            val bundle = bundleOf("families" to families)
            navigateUp(5, bundle)
        }
    }

    private fun setRequestAdapter(families: ArrayList<Family>) {
        familiesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItemDecoration(60))
            adapter = familyAdapter
        }
        familyAdapter.updateData(families)
    }

    override fun onDeleteClick(position: Int) {
        families.removeAt(position)
        familyAdapter.updateData(families)
    }

    override fun onEditClick(position: Int) {
        var bundle = bundleOf("position" to position, "fromScreen" to AddFamilyFragment.ScreenType.FAMILY_DETAILS, "defaultFamilyValues" to families[position])
        navigate(R.id.action_add_family, bundle, 6)
    }

    override fun onFragmentResult(requestCode: Int, bundle: Bundle) {
        super.onFragmentResult(requestCode, bundle)

        when (requestCode) {
            6 -> {
                val family = bundle.get("family") as Family?
                val position = bundle.get("position") as Int?
                if (family != null && position != null && position != -1) {
                    families[position] = family
                    familyAdapter.updateData(families)
                }
            }
        }
    }

}
