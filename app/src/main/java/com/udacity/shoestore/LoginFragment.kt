package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var preferences: MyPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        preferences = MyPreferences(requireActivity())

        binding.loginButton.setOnClickListener { view: View ->
            nullCheck(view)
        }

        binding.registerButton.setOnClickListener { view: View ->
            nullCheck(view)
        }

        return binding.root
    }

    private fun nullCheck(view: View) {
        when (binding.emailField.text.isNotBlank() && binding.passwordField.text.isNotBlank()) {
            true -> {
                preferences.setLoginState(true)
                view.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
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
}