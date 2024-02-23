package com.andrew.roomexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrew.roomexample1.databinding.ActivityMainBinding
import com.andrew.roomexample1.db.Subscriber
import com.andrew.roomexample1.db.SubscriberDAO
import com.andrew.roomexample1.db.SubscriberDatabase
import com.andrew.roomexample1.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    /** Creating the new class level variable for the RecyclerView Adapter
     * just for having just one reference*/
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        /** Declaring model and repository object in order to use the viewModelFactory **/
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        // Assigning the data variable from xml to the viewModel
        binding.myViewModel = subscriberViewModel
        // Declare this for using LiveData
        binding.lifecycleOwner = this

        setupRecyclerView()

        /** Observing the events and displaying the message*/
        subscriberViewModel.message.observe(this){
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.subcriberRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter{ selectedSubscriber: Subscriber -> itemListClicked(selectedSubscriber) }
        binding.subcriberRecyclerview.adapter = adapter
        displaySubscribersList()
    }

    /** function to observe the list of subscriber's data in the database table **/
    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this) {
            Log.i("MyTag", "subscribers -> $it")
            /** We're about to add the click listener to the RecyclerViewAdapter
             * by a Lambda expression**/
            //binding.subcriberRecyclerview.adapter = MyRecyclerViewAdapter(it)
            /*binding.subcriberRecyclerview.adapter = MyRecyclerViewAdapter(it
            ) { selectedSubscriber: Subscriber -> itemListClicked(selectedSubscriber) }*/
            /** We are re-using the same class level variable called adapter to change the
             * list of subcribers on the RecyclerView*/
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun itemListClicked(subscriber: Subscriber){
        //Toast.makeText(this, "Selected subscriber is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.updateOrDeleteSubscriber(subscriber)
    }
}