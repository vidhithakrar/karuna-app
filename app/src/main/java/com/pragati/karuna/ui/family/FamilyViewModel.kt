    package com.pragati.karuna.ui.family

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.pragati.karuna.models.Family

    class FamilyViewModel : ViewModel() {
        val family = MutableLiveData<Family>()
    }