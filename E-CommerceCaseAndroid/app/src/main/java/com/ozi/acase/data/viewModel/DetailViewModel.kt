
// DetailViewModel.kt
package com.ozi.acase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozi.acase.data.model.Product
import com.ozi.acase.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getProductDetail(id: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = repository.getProductDetail(id)
                _product.value = result
                _loading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _loading.value = false
            }
        }
    }
}