package com.example.navexample1

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navexample1.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using DataBinding object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.button.setOnClickListener {
            binding.apply {
                if(!TextUtils.isEmpty(editText.text.toString())){
                    val bundle = bundleOf("input_text" to editText.text.toString())
                    it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
                }
                else {
                    Toast.makeText(activity, "Please insert your name", Toast.LENGTH_LONG).show()
                }
            }

        }
        return binding.root
    }
}