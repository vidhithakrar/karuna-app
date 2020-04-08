package com.pragati.karuna.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.models.Family
import com.pragati.karuna.models.Kit
import com.pragati.karuna.models.Location
import com.pragati.karuna.viewmodel.HomeViewModel
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
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.families.observe(viewLifecycleOwner, Observer { families ->
            textView.text = "${families.size} Families"
        })

        homeViewModel.location.observe(viewLifecycleOwner, Observer { location ->
            tv_1.text = location.address
        })

        homeViewModel.kit.observe(viewLifecycleOwner, Observer { kit ->
            tv_2.text = kit.description
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
        btn1.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_location, 0) })

        btn2.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_family, 1) })

        btn3.setOnClickListener(View.OnClickListener { navigate(R.id.action_add_kit, 2) })

        btn4.setOnClickListener(View.OnClickListener {
            homeViewModel.addRequest()
        })
    }
}
