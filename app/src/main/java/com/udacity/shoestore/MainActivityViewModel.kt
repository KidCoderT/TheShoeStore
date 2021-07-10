package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel: ViewModel() {
    private val _shoeListItemsData = MutableLiveData<MutableList<Shoe>>()
    val shoeListItemsData: LiveData<MutableList<Shoe>> get() = _shoeListItemsData

    fun addShoe(name: String, company: String, size: Int, description: String) {
        _shoeListItemsData.value?.add(Shoe(name, size, company, description))
    }
}