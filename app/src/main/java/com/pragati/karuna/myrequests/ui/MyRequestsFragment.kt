package com.pragati.karuna.myrequests.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.phelat.navigationresult.BundleFragment
import com.pragati.karuna.R
import com.pragati.karuna.ViewModelFactory
import com.pragati.karuna.home.ui.MainActivity
import com.pragati.karuna.login.model.LoggedInUser
import com.pragati.karuna.myrequests.adapter.MyRequestsAdapter
import com.pragati.karuna.myrequests.adapter.OnItemClickListener
import com.pragati.karuna.myrequests.adapter.VerticalSpaceItemDecoration
import com.pragati.karuna.myrequests.viewmodel.MyRequestsViewModel
import com.pragati.karuna.request.model.Request
import kotlinx.android.synthetic.main.fragment_my_requests.*

class MyRequestsFragment : BundleFragment(), OnItemClickListener {

    private lateinit var requestsViewModel: MyRequestsViewModel
    private var myRequestsAdapter: MyRequestsAdapter = MyRequestsAdapter(mutableListOf(), this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestsViewModel =
            ViewModelProviders.of(
                this,
                ViewModelFactory(arguments?.getParcelable(MainActivity.UserExtra))
            ).get(MyRequestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_requests, container, false)

        requestsViewModel.requests.observe(viewLifecycleOwner, Observer { myRequests ->
            setRequests(myRequests)
        })

        requestsViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            setErrorObserver(error)
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWelcomeMessage()
        setRequestAdapter()
        requestsViewModel.loadRequests()
        fab.setOnClickListener {
            val bundle = bundleOf(
                MainActivity.UserExtra to arguments?.getParcelable<LoggedInUser>(MainActivity.UserExtra)
            )
            navigate(R.id.action_home, bundle, 4)
        }
    }

    private fun setWelcomeMessage() {
        val username = requestsViewModel.loggedInUser.email.split("@").first()
        welcome.text = resources.getString(R.string.welcome_user, username)
    }

    private fun setRequests(myRequests: List<Request>) {
        myRequestsAdapter.myRequests = myRequests
        if (!myRequests.isEmpty()) {
            setEmptyView(false)
            myRequestsAdapter.notifyDataSetChanged()
        } else {
            setEmptyView(true)
        }
        progress_circular.visibility = View.GONE
    }

    private fun setErrorObserver(error: String?) {
        setEmptyView(true)
        progress_circular.visibility = View.GONE
    }

    private fun setEmptyView(isVisible: Boolean) {
        if (isVisible) {
            welcome.setTextColor(resources.getColor(R.color.text_color))
            rv_my_requests.visibility = View.GONE
            cl_container_empty_view.visibility = View.VISIBLE
        } else {
            welcome.setTextColor(resources.getColor(R.color.colorPrimary))
            rv_my_requests.visibility = View.VISIBLE
            cl_container_empty_view.visibility = View.GONE
        }
    }

    private fun setRequestAdapter() {
        progress_circular.visibility = View.VISIBLE
        rv_my_requests.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(VerticalSpaceItemDecoration(60))
            adapter = myRequestsAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val bundle = bundleOf(
            "request" to requestsViewModel.requests.value?.get(position),
            MainActivity.UserExtra to arguments?.getParcelable<LoggedInUser>(MainActivity.UserExtra)
        )
        navigate(R.id.action_home, bundle, 4)
    }
}
