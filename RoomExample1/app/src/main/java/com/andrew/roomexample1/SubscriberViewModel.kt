package com.andrew.roomexample1

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.roomexample1.db.Subscriber
import com.andrew.roomexample1.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** Because this viewModel has  a constructor parameter, we need to create a viewModelFactory **/
class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscribers = repository.subscribers

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    /** New variables to control the update and deletion flows**/
    private var isUpdateOrDelete = false
    private lateinit var updateOrDeleteSubscriber: Subscriber

    /** Creating a new variable to handle the status for db operations*/
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        /** Adding inputName and inputEmail validations*/
        if(inputName.value == null){
            statusMessage.value = Event("Please enter subscriber's name")
        }
        else if(inputEmail.value == null){
            statusMessage.value = Event("Please enter subscriber's email")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value = Event("Please enter a valid email address")
        }
        else {
            if (!isUpdateOrDelete) {
                val name = inputName.value!!
                val email = inputEmail.value!!
                /** The id here is ignored by Room because we tell Room that primary key was going
                 * to be autoincremental **/
                insert(Subscriber(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
            } else {
                updateOrDeleteSubscriber.name = inputName.value!!
                updateOrDeleteSubscriber.email = inputEmail.value!!
                update(updateOrDeleteSubscriber)
            }
        }

    }

    fun clearAllOrDelete() {
        if (!isUpdateOrDelete) {
            clearAll()
        } else {
            delete(updateOrDeleteSubscriber)
        }
    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(subscriber)
        /** Modifying the function to edit statusMessage value */
        withContext(Dispatchers.Main){
            if(newRowId > 0) {
                statusMessage.value = Event("Subscriber ${subscriber.name} inserted successfully!")
            }
            else {
                statusMessage.value = Event("Error inserting subscriber!")
            }
        }
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRows = repository.update(subscriber)
        /** using Coroutines to change the view**/
        withContext(Dispatchers.Main) {
            if(numberOfRows > 0) {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("Subscriber ${subscriber.name} updated successfully!")
            }
            else {
                statusMessage.value = Event("Error updating subscriber!")
            }
        }
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.delete(subscriber)
        withContext(Dispatchers.Main) {
            if(numberOfRowsDeleted > 0) {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("Subscriber ${subscriber.name} deleted successfully!")
            }
            else {
                statusMessage.value = Event("Error deleting subscriber!")
            }
        }
    }

    private fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.deleteAll()
        withContext(Dispatchers.Main){
            if(numberOfRowsDeleted > 0) {
                statusMessage.value = Event("$numberOfRowsDeleted subscribers deleted successfully!")
            }
            else {
                statusMessage.value = Event("Error deleting subscribers!")
            }
        }
    }

    fun updateOrDeleteSubscriber(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        updateOrDeleteSubscriber = subscriber
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


}