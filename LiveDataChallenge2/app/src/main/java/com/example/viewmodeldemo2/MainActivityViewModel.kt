package com.example.viewmodeldemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    private var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
        get() = total

    var inputText = MutableLiveData<String>()

    init {
        total.value = startingTotal
    }

    //Change method name accordingly
    fun addValue() {
        val intInput = inputText.value!!.toInt()
        total.value = (total.value)?.plus(intInput)
    }
}