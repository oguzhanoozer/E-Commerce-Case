// ProductRepository.kt
package com.ozi.acase.data.repository

import com.ozi.acase.data.api.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllProducts() = apiService.getAllProducts()

    suspend fun getSliderProducts() = apiService.getSliderProducts()

    suspend fun getProductDetail(id: Int) = apiService.getProductDetail(id)
}