package com.example.viewmodelchallenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelchallenge1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /** Global variables declaration **/
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBinding assignation
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //ViewModel assignation
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.apply {
            valueToAddET.text = viewModel.getValue().toString().toEditable()
            resultTV.text = viewModel.getResult()
            addBtn.setOnClickListener{
                viewModel.add(valueToAddET.text.toString().toInt())
                resultTV.text = viewModel.getResult()
            }
        }

    }
}