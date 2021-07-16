package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shoe_list_item.view.*


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var preferences: MyPreferences
    private var loginState: Boolean = false
    private val sharedViewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        preferences = MyPreferences(requireActivity())
        loginState = preferences.getLoginState()

        sharedViewModel.shoeListItemsData.observe(viewLifecycleOwner, {
            binding.shoeListingsContainer.removeAllViews()
            for (shoeItem in it) {
                createNewShoe(shoeItem.name, shoeItem.company, shoeItem.size, shoeItem.description)
            }
            if (binding.shoeListingsContainer.childCount == 0) {
                binding.noShoesTextImageView.visibility = View.VISIBLE
            } else {
                binding.noShoesTextImageView.visibility = View.GONE
            }
        })

        binding.fab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
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

        if (binding.shoeListingsContainer.childCount == 0) {
            binding.noShoesTextImageView.visibility = View.VISIBLE
        } else {
            binding.noShoesTextImageView.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        preferences.setLoginState(false)
        return super.onOptionsItemSelected(item)
    }

    private fun createNewShoe(name: String, company: String, size: String, description: String) {
        val linearLayout: LinearLayout = binding.shoeListingsContainer
        val view: View = layoutInflater.inflate(R.layout.shoe_list_item, null)

        val nameView: TextView = view.findViewById(R.id.name)
        nameView.text = name
        val companyView: TextView = view.findViewById(R.id.company)
        companyView.text = company
        val sizeTextView: TextView = view.findViewById(R.id.size)
        sizeTextView.text = getString(R.string.size_text, size)
        val descriptionTextView: TextView = view.findViewById(R.id.description)
        descriptionTextView.text = description

        linearLayout.addView(view)
    }
}