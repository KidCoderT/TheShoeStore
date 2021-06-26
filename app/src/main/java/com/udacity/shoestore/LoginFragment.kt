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
            val emailFieldText = binding.emailField.text
            val passwordFieldText = binding.passwordField.text

            val isNull = emailFieldText.equals("") || !passwordFieldText.equals("")

            var errorText = ""

            if (emailFieldText.equals("")) {
                binding.errorText.visibility = View.VISIBLE
                errorText = "$errorText*email cannot be nullable."
            }

            if (passwordFieldText.equals("")) {
                binding.errorText.visibility = View.VISIBLE
                errorText = "$errorText *password cannot be nullable."
            }

            binding.errorText.text = errorText

            if (isNull) {
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
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