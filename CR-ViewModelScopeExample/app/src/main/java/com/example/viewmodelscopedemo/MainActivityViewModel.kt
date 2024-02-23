package com.example.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelscopedemo.model.User
import com.example.viewmodelscopedemo.model.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivityViewModel : ViewModel() {

    /** In order to control the coroutines launch in the scope we create a Job()
     * and pass it as a context for the CoroutineScope (+)**/
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)
    private val userRepository: UserRepository = UserRepository()
    val users: MutableLiveData<List<User>?> = MutableLiveData()

    fun getUserData() {

        /** If we use the viewModelScope we don't need to declare a Job nor a different scope nor
         * cancelling the coroutine on onCleared() method. The latter would be done automatically**/
        /*myScope.launch {
            //write some code
        }*/
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO){
                result = userRepository.getUsers()
            }
            users.value = result
        }

    }

    /*override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }*/


}