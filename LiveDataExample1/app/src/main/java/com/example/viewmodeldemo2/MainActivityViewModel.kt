package com.example.viewmodeldemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
    /** without encapsulation**/
    private var total = MutableLiveData<Int>() // MutableLiveData lets us to read/write variables

    /** Using encapsulation**/
    val totalData : LiveData<Int>
        get() = total

    init {
        total.value = startingTotal
    }

    //Using LiveData this method is not used.
    /*fun getTotal():Int{
        return total
    }*/

    fun setTotal(input:Int){
        total.value = (total.value)?.plus(input)
    }
}