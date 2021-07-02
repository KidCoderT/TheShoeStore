package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        binding.fabCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment(
                    null,
                    null,
                    0,
                    null
                )
            )
        )

        binding.fabContinue.setOnClickListener { view: View ->
            val shoeName: String = binding.nameTextField.text.toString()
            val companyName: String = binding.companyNameTextField.text.toString()
            val shoeSize: Int = binding.shoeSizeNumberField.text.toString().toInt()
            val shoeDescription: String = binding.descriptionTextField.text.toString()

            when (shoeName.isNotBlank() && companyName.isNotBlank() && shoeSize != 0 && shoeDescription.isNotBlank()) {
                true -> {
                    view.findNavController()
                        .navigate(
                            ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment(
                                shoeName,
                                companyName,
                                shoeSize,
                                shoeDescription
                            )
                        )
                }
                false -> {
                    val errorText = "Non of the fields Are nullable"
                    binding.errorText.text = errorText
                }
            }
        }

        return binding.root
    }
}