package com.example.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        /** Since we are using the LiveData to update information on the view
         * LiveData needs to be aware of lifecycleOwners that's why we need to declare
         * the following**/
        binding.lifecycleOwner = this //We saying the binding data object is bound to the MainActivity lifecycle
        binding.myViewModel = viewModel
        viewModel.countData.observe(this, Observer {
            binding.countText.text = it.toString()
        })
    }
}
