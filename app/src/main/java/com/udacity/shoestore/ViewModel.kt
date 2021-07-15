package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ViewModel: ViewModel() {

    val shoesList = mutableListOf<Shoe>()

    private val _shoeListItemsData = MutableLiveData(shoesList)
    val shoeListItemsData: LiveData<MutableList<Shoe>>
        get() = _shoeListItemsData

    init {
        shoesList.clear()
        _shoeListItemsData.value = shoesList
    }

    fun addShoe(name: String, company: String, size: String, description: String) {
        shoesList.add(Shoe(name, size, company, description))
        _shoeListItemsData.value = shoesList
    }
}