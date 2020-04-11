package com.pragati.karuna.myrequests.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.myrequests.adapter.MyRequestsAdapter
import com.pragati.karuna.myrequests.adapter.VerticalSpaceItemDecoration
import com.pragati.karuna.myrequests.viewmodel.MyRequestsViewModel
import com.pragati.karuna.request.model.Request
import kotlinx.android.synthetic.main.fragment_my_requests.*

class MyRequestsFragment : BundleFragment() {

    private lateinit var requestsViewModel: MyRequestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestsViewModel =
            ViewModelProviders.of(this).get(MyRequestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_requests, container, false)
        requestsViewModel.requests.observe(viewLifecycleOwner, Observer {
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_my_requests.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItemDecoration(60))
            adapter = MyRequestsAdapter(
                listOf(
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request(),
                    Request()
                )
            )
        }
    }
}
