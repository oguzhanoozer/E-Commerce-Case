package com.ozi.acase.data.service

import com.ozi.acase.data.model.Product

interface ProductService {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetail(id: Int): Product
    suspend fun getSliderProducts(): List<Product>
}