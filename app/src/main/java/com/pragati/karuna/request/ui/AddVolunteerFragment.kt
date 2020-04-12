package com.pragati.karuna.request.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.phelat.navigationresult.navigateUp
import com.pragati.karuna.R
import com.pragati.karuna.request.adapter.VolunteersAdapter
import com.pragati.karuna.request.model.Volunteer
import com.pragati.karuna.request.viewmodel.VolunteerViewModel
import com.pragati.karuna.util.disable
import com.pragati.karuna.util.enable
import kotlinx.android.synthetic.main.fragment_add_volunteer.*

class AddVolunteerFragment : BundleFragment() {

    private lateinit var volunteerViewModel: VolunteerViewModel

    companion object {
        const val VOLUNTEER = "volunteer"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        volunteerViewModel = ViewModelProviders.of(this).get(VolunteerViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_volunteer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirm_volunteers_button?.disable()
        volunteers_list_container?.layoutManager = LinearLayoutManager(context)
        volunteers_list_container?.adapter = VolunteersAdapter(context!!, object :
            AddSuppliersFragment.OnSupplierSelected {
            override fun onSelected() {
                confirm_volunteers_button.enable()
            }
        })

        confirm_volunteers_button?.setOnClickListener(View.OnClickListener {
            val taggedVolunteer =
                (volunteers_list_container?.adapter as VolunteersAdapter).getTaggedVolunteers()[0]
            val bundle = bundleOf(VOLUNTEER to taggedVolunteer)
            navigateUp(10, bundle)
        })

        volunteerViewModel.volunteers.observe(viewLifecycleOwner, Observer {
            val selectedVolunteer = arguments?.get(VOLUNTEER) as? Volunteer
            (volunteers_list_container?.adapter as VolunteersAdapter).setVolunteers(
                volunteerViewModel.volunteers.value!!, selectedVolunteer
            )
            if (selectedVolunteer != null) {
                confirm_volunteers_button.enable()
            } else {
                confirm_volunteers_button.disable()
            }
        })

        volunteerViewModel.fetchVolunteers()
    }

}
