package com.example.navdemo5


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
import com.example.navdemo5.databinding.FragmentEmailBinding

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        binding.apply {
            submitButton.setOnClickListener {
                val name = requireArguments().getString("input_name")
                if(!TextUtils.isEmpty(emailEditText.text)){
                    val bundle = bundleOf(
                        "input_name" to name,
                        "input_email" to emailEditText.text.toString()
                    )
                    it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
                }else {
                    toastMessage(context, "Please, enter your email")
                }
            }

        }
        return binding.root
    }
}
