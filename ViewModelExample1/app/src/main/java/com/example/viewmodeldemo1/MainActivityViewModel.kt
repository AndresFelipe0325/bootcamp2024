package com.example.viewmodeldemo1

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var count = 0


    /** Methods created for returning the current value
     * and for updating the count value**/
    fun getCurrentCount() : Int {
        return count
    }

    fun getUpdatedCount(): Int {
        return ++count
    }
}