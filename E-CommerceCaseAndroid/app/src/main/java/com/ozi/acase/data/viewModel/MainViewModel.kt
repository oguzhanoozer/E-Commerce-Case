// MainViewModel.kt
package com.ozi.acase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozi.acase.data.model.Product
import com.ozi.acase.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _sliderProducts = MutableLiveData<List<Product>>()
    val sliderProducts: LiveData<List<Product>> = _sliderProducts

    private val _gridProducts = MutableLiveData<List<Product>>()
    val gridProducts: LiveData<List<Product>> = _gridProducts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                _loading.value = true

                // Parallel requests
                val sliderDeferred = async { repository.getSliderProducts() }
                val gridDeferred = async { repository.getAllProducts() }

                _sliderProducts.value = sliderDeferred.await()
                _gridProducts.value = gridDeferred.await()

                _loading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _loading.value = false
            }
        }
    }
}
