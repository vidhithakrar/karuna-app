package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Volunteer
import com.pragati.karuna.request.repository.VolunteerCompletionListener
import com.pragati.karuna.request.repository.VolunteerRepository

class VolunteerViewModel : ViewModel() {
    var volunteers = MutableLiveData<List<Volunteer>>()

     fun fetchVolunteers() {
        VolunteerRepository().fetchVolunteer(object :
            VolunteerCompletionListener {
            override fun onComplete(volunteer: List<Volunteer>) {
                volunteers.postValue(volunteer)
            }

            override fun onError() {

            }
        })
     }
}