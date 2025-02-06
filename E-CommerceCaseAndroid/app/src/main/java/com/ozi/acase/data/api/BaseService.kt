// data/service/base/BaseService.kt
package com.ozi.acase.data.service.base

import com.ozi.acase.data.model.Product

interface BaseService {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetail(id: Int): Product
    suspend fun getSliderProducts(): List<Product>
}