package com.ozi.acase.data.service.base

import com.ozi.acase.data.model.Product

interface BaseService {
    suspend fun getProducts(page: Int, limit: Int): List<Product>
    suspend fun getProductDetail(id: Int): Product
    suspend fun getSliderProducts(): List<Product>
}