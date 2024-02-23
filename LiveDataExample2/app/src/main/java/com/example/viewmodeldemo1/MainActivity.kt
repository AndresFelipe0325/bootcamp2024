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
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //Binding data from view (myViewModel variable) to the viewModel Object
        binding.myViewModel = viewModel
        viewModel.count.observe(this) {
            binding.countText.text = it.toString()
        }
        /** Since the variable myViewModel is linked to the viewModel and it's being called
         * directly from the onClick attribute on the xml file. We can delete this part of code**/
        /*binding.button.setOnClickListener {
            viewModel.updateCount()
        }*/
    }
}
