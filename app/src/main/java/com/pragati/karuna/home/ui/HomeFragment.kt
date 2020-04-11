package com.pragati.karuna.home.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.ViewModelFactory
import com.pragati.karuna.core.models.RequestItem
import com.pragati.karuna.home.viewmodel.HomeViewModel
import com.pragati.karuna.request.model.*
import com.pragati.karuna.util.gone
import com.pragati.karuna.util.visible
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_request_summary_collapse.view.*
import kotlinx.android.synthetic.main.view_request_summary_expand.view.*

class HomeFragment : BundleFragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this, ViewModelFactory()).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.families.observe(viewLifecycleOwner, Observer { families ->
            if (families.size > 0) {
                familiesView.bindExpandedState(RequestItem.FamilyItem(families = families))
                validateRequestData()
            }
        })

        homeViewModel.location.observe(viewLifecycleOwner, Observer { location ->
            locationDetailView.bindExpandedState(RequestItem.LocationItem(location = location))
            validateRequestData()
        })

        homeViewModel.kit.observe(viewLifecycleOwner, Observer { kit ->
            kitDetailView.bindExpandedState(RequestItem.KitItem(kit = kit))
            validateRequestData()
        })

        homeViewModel.supplier.observe(viewLifecycleOwner, Observer { supplier ->
            suppliersView.bindExpandedState(RequestItem.SupplierItem(supplier = supplier))
        })

        homeViewModel.requestState.observe(viewLifecycleOwner, Observer { request ->
            loading.gone()
        })

        return root
    }

    private fun setRequestData(request: Request) {
        createRequestButton.setText(getString(R.string.update_request))
        homeViewModel.families.value = request.families
        homeViewModel.location.value = request.location
        homeViewModel.kit.value = request.kit
    }

    private fun validateRequestData() {
        createRequestButton.isEnabled =
            homeViewModel.families.value?.isNotEmpty()!! && homeViewModel.location.value != null && homeViewModel.kit.value != null
    }

    override fun onFragmentResult(requestCode: Int, bundle: Bundle) {
        super.onFragmentResult(requestCode, bundle)

        when (requestCode) {
            0 -> {
                val location = bundle?.get("location") as Location?
                location?.let {
                    homeViewModel.addLocation(it)
                }
            }

            1 -> {
                val family = bundle?.get("family") as Family?
                family?.let {
                    homeViewModel.addFamily(it)
                }
            }

            2 -> {
                val kit = bundle?.get("kit") as Kit?
                kit?.let { homeViewModel.addKit(it) }
            }

            3 -> {
                val supplier = bundle.get("supplier") as Supplier?
                supplier?.let { homeViewModel.addSuppliers(supplier) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.get("request")?.let { request ->
            setRequestData(request as Request)
        }

        createRequestButton.setOnClickListener {
            homeViewModel.addRequest()
        }

        closeRequestButton.setOnClickListener {
            displayConfirmationDialog {
                loading.visible()
                homeViewModel.closeRequest()
            }
        }

        locationDetailView.addDetails.setOnClickListener {
            navigateToLocationDetails()
        }

        locationDetailView.actionButton.setOnClickListener {
            navigateToLocationDetails()
        }

        familiesView.addDetails.setOnClickListener {
            navigateToFamilyDetails()
        }

        familiesView.actionButton.setOnClickListener {
            navigateToFamilyDetails()
        }

        kitDetailView.addDetails.setOnClickListener {
            navigateToKitDetails()
        }

        kitDetailView.actionButton.setOnClickListener {
            navigateToKitDetails()
        }

        suppliersView.addDetails.setOnClickListener {
            navigateToSupplierDetails()
        }

        suppliersView.actionButton.setOnClickListener {
            navigateToSupplierDetails()
        }
    }

    private fun displayConfirmationDialog(action: () -> Unit) {
        AlertDialog.Builder(activity as Context, R.style.alertDialog)
            .setTitle(R.string.confirm)
            .setMessage(R.string.request_close_confirmation_message)
            .setPositiveButton(
                R.string.yes
            ) { d, _ ->
                d.dismiss()
                action()
            }
            .setNegativeButton(R.string.no) { d, _ -> d.dismiss() }
            .show()
    }

    private fun navigateToSupplierDetails() {
        navigate(
            R.id.action_add_suppliers,
            3
        )
    }

    private fun navigateToKitDetails() {
        navigate(R.id.action_add_kit, 2)
    }

    private fun navigateToFamilyDetails() {
        navigate(
            R.id.action_add_family,
            1
        )
    }

    private fun navigateToLocationDetails() {
        navigate(
            R.id.action_add_location,
            0
        )
    }
}
