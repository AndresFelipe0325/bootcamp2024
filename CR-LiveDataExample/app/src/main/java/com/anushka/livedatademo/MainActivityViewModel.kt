package com.anushka.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.anushka.livedatademo.model.User
import com.anushka.livedatademo.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel()  {

    private var usersRepository= UserRepository()

    /** Coroutines: This code is using LiveData builder**/
    var users = liveData(Dispatchers.IO){
        val result = usersRepository.getUsers()
        emit(result)
    }

    /** The code below is using ViewModelScope so we're going to comment it**/
    /*var users: MutableLiveData<List<User>?> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO) {
                result = usersRepository.getUsers()
            }
            users.value = result
        }
    }*/
}