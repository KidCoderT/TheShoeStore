package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val sharedViewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        val shoe = Shoe("", "", "", "")
        binding.shoe = shoe
        binding.lifecycleOwner = this

        binding.fabCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
            )
        )

        binding.fabContinue.setOnClickListener { view: View ->
            when (shoe.name.isNotBlank() && shoe.company.isNotBlank() && shoe.size.isNotEmpty() && shoe.description.isNotBlank()) {
                true -> {
                    sharedViewModel.addShoe(shoe)
                    view.findNavController()
                        .navigate(
                            ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
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