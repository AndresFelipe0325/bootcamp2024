package com.example.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private var count = 0 removed from here, created inside viewmodel class
    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //binding.countText.text = count.toString() unused becasue of viewmodel
        //Using viewmodel class to get the count value
        binding.countText.text = viewModel.getCurrentCount().toString()
        binding.button.setOnClickListener {
            //No need to do this anymore
            /*count++
            binding.countText.text = count.toString()*/

            binding.countText.text = viewModel.getUpdatedCount().toString()
        }
    }
}