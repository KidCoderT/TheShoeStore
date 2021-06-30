package com.udacity.shoestore

import android.R.attr
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import timber.log.Timber


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

        binding.fab.setOnClickListener { view:View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }

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
        val view: View = layoutInflater.inflate(R.layout.shoe_list_item, null)

        val nameView: TextView = view.findViewById(R.id.name)
        nameView.text = name
        val companyView: TextView = view.findViewById(R.id.company)
        companyView.text = company
        val sizeTextView: TextView = view.findViewById(R.id.size)
        sizeTextView.text = "$size size"
        val descriptionTextView: TextView = view.findViewById(R.id.description)
        descriptionTextView.text = description

        linearLayout.addView(view)
    }
}