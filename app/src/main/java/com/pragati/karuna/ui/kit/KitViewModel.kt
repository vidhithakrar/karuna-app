package com.pragati.karuna.ui.kit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Kit

class KitViewModel : ViewModel() {
    val kit = MutableLiveData<Kit>()
}
