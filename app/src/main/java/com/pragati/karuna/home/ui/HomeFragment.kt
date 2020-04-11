package com.pragati.karuna.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.core.models.RequestItem
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.model.Location
import com.pragati.karuna.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BundleFragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.families.observe(viewLifecycleOwner, Observer { families ->
        })

        homeViewModel.location.observe(viewLifecycleOwner, Observer { location ->
        })

        homeViewModel.kit.observe(viewLifecycleOwner, Observer { kit ->
        })

        return root
    }

    override fun onFragmentResult(requestCode: Int, bundle: Bundle) {
        super.onFragmentResult(requestCode, bundle)

        when (requestCode) {
            0 -> {
                var location = bundle?.get("location") as Location?
                location?.let { homeViewModel.addLocation(it) }
            }

            1 -> {
                var family = bundle?.get("family") as Family?
                family?.let {
                    homeViewModel.addFamily(it)
                }
            }

            2 -> {
                var kit = bundle?.get("kit") as Kit?
                kit?.let { homeViewModel.addKit(it) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationDetailView.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_location, 0) })

        familiesView.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_family, 1) })

        kitDetailView.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_kit, 2) })

        suppliersView.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_suppliers, 3) })
    }
}
