package com.pragati.karuna.ui.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pragati.karuna.R
import kotlinx.android.synthetic.main.fragment_add_location.*

class AddFamiyFragment : Fragment() {

    private lateinit var familyViewModel: FamilyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        familyViewModel =
            ViewModelProviders.of(this).get(FamilyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_family, container, false)
        familyViewModel.families.observe(viewLifecycleOwner, Observer {
            Log.d("Add data", "Data")
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_next.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.nav_home)
        })
    }
}
