package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import java.security.AccessController.getContext


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    lateinit var preferences: MyPreferences
    var loginState: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        preferences = MyPreferences(requireActivity())
        loginState = preferences.getLoginState()
        newShoe("Nike Boys", "Tejas Cop.", "10", "Amazing Shoes. The best!")

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!loginState) {
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        preferences.preference.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (!sharedPreferences.getBoolean(key, false)) {
                view.findNavController()
                    .navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.login_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.menu.login_menu) {
            preferences.setLoginState(false)
        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun newShoe(name: String, company: String, size: String, description: String) {
        val linearLayout: LinearLayout = binding.shoeListingsContainer
        val constraintLayout =
            View.inflate(context, R.layout.shoe_list_item, null) as ConstraintLayout
        constraintLayout.findViewById(R.id.name) = name
        constraintLayout.findViewById(R.id.company) = company
        constraintLayout.findViewById(R.id.size) = "$size size"
        constraintLayout.findViewById(R.id.description) = description
        linearLayout.addView(constraintLayout)
    }
}