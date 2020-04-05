package com.pragati.karuna.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pragati.karuna.R
import kotlinx.android.synthetic.main.fragment_add_location.*

class AddKitFragment : Fragment() {

    private lateinit var kitViewModel: KitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kitViewModel =
            ViewModelProviders.of(this).get(KitViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_kit, container, false)
        kitViewModel.location.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_home)
        })
    }
}
