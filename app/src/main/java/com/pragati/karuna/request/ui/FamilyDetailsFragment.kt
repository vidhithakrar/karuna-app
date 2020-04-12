package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.myrequests.adapter.VerticalSpaceItemDecoration
import com.pragati.karuna.request.adapter.FamilyDetailsAdapter
import com.pragati.karuna.request.adapter.OnFamilyItemClickListener
import com.pragati.karuna.request.model.Family
import kotlinx.android.synthetic.main.fragment_family_details.*
import java.util.ArrayList

class FamilyDetailsFragment : BundleFragment(), OnFamilyItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_family_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelableArrayList<Family>("families")?.let { families ->
            setRequestAdapter(families)
        }
    }

    private fun setRequestAdapter(families: ArrayList<Family>) {
        familiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItemDecoration(60))
            adapter = FamilyDetailsAdapter(families, this@FamilyDetailsFragment)
        }
    }

    override fun onDeleteClick(position: Int) {
    }

    override fun onEditClick(position: Int) {
//        var bundle = bundleOf("position" to position)
//        navigate(R.id.action_add_family, bundle, 4)
    }
}
