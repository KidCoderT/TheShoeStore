package com.udacity.shoestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.loginButton.setOnClickListener { view: View ->
            when (binding.emailField.text.isNotBlank() && binding.passwordField.text.isNotBlank()) {
                true -> {
                    // update your isLoggedIn variable
                    view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
                false -> {
                    var errorText = ""
                    if (binding.emailField.text.isBlank()) {
                        errorText = "$errorText*email cannot be null."
                    }
                    if (binding.passwordField.text.isBlank()) {
                        errorText = "$errorText *password cannot be null."
                    }
                    binding.errorText.text = errorText
                }
            }
        }

        binding.registerButton.setOnClickListener { view: View ->
            nextScreen(view)
        }

        return binding.root
    }

    private fun nextScreen(view:View) {
        view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}