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
import kotlinx.android.synthetic.main.fragment_my_requests.*

class MyRequestsFragment : BundleFragment() {

    private lateinit var requestsViewModel: MyRequestsViewModel
    private var myRequestsAdapter: MyRequestsAdapter = MyRequestsAdapter(mutableListOf())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestsViewModel =
            ViewModelProviders.of(this).get(MyRequestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_requests, container, false)

        setRequestsObserver()
        setErrorObserver()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRequestAdapter()

        fab.setOnClickListener {
            navigate(R.id.action_home, 4)
        }
    }

    private fun setRequestsObserver() {
        requestsViewModel.requests.observe(viewLifecycleOwner, Observer { myRequests ->
            myRequestsAdapter.myRequests = myRequests
            if (!myRequests.isEmpty()) {
                setEmptyView(false)
                myRequestsAdapter.notifyDataSetChanged()
            } else {
                setEmptyView(true)
            }
        })
    }

    private fun setErrorObserver() {
        requestsViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            setEmptyView(true)
        })
    }

    private fun setEmptyView(isVisible: Boolean) {
        if (isVisible) {
            rv_my_requests.visibility = View.GONE
            cl_container_empty_view.visibility = View.VISIBLE
        } else {
            rv_my_requests.visibility = View.VISIBLE
            cl_container_empty_view.visibility = View.GONE
        }
    }

    private fun setRequestAdapter() {
        rv_my_requests.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItemDecoration(60))
            adapter = myRequestsAdapter
        }
        requestsViewModel.loadRequests()
    }
}
