package com.example.viewmodelchallenge1

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var valueToAdd = 0
    private var result = 0

    fun getValue(): String {
        return valueToAdd.toString()
    }

    fun getResult(): String {
        return result.toString()
    }

    fun add(value: Int) {
        valueToAdd = value
        result += value
    }


}