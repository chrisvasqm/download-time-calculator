package com.christianv07.dev.speedy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _result = MutableLiveData<String>()

    val result: LiveData<String>
        get() = _result

}
