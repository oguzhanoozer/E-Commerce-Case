package com.ozi.acase.data.repository

import com.ozi.acase.data.service.base.BaseService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val service: BaseService
) {
    suspend fun getAllProducts() = service.getProducts()
    suspend fun getSliderProducts() = service.getSliderProducts()
    suspend fun getProductDetail(id: Int) = service.getProductDetail(id)
}