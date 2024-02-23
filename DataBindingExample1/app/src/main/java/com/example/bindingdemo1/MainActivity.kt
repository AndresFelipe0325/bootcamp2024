package com.example.bindingdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // setContentView(R.layout.activity_main) replace by DataBinding
        // deprecated
        /*val button = findViewById<Button>(R.id.submit_button)
        button.setOnClickListener {
            displayGreeting()
        }*/
        binding.submitButton.setOnClickListener {
            displayGreeting()
        }
    }

    private fun displayGreeting() {
        // deprecated
        /*val messageView = findViewById<TextView>(R.id.greeting_text_view)
        val nameText = findViewById<EditText>(R.id.name_edit_text)

        messageView.text = "Hello! "+ nameText.text*/

        // option 1
        //binding.greetingTextView.text = "Hello! ${binding.nameEditText.text}"

        //option 2
        binding.apply {
            greetingTextView.text =  "Hello! ${nameEditText.text}"
        }
    }
}