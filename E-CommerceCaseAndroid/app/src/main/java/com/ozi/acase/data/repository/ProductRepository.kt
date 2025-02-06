package com.ozi.acase.data.repository

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.service.base.BaseService
import com.ozi.acase.utils.Result
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val service: BaseService
) {
    suspend fun getAllProducts(): Result<List<Product>> =
        try {
            val response = service.getProducts()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Could not load products. Please try again.")
        }

    suspend fun getSliderProducts(): Result<List<Product>> =
        try {
            val response = service.getSliderProducts()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Could not load slider products. Please try again.")
        }

    suspend fun getProductDetail(id: Int): Result<Product> =
        try {
            val response = service.getProductDetail(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Could not load product details. Please try again.")
        }
}