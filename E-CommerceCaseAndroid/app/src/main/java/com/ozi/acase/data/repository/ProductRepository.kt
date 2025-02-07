package com.ozi.acase.data.repository

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.service.base.BaseService
import com.ozi.acase.utils.Constants
import com.ozi.acase.utils.Result
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val service: BaseService
) {
    suspend fun getProducts(offset: Int, limit: Int): Result<List<Product>> =
        try {
            val response = service.getProducts(offset, limit)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(Constants.ErrorMessages.PRODUCTS_LOAD)
        }

    suspend fun getSliderProducts(): Result<List<Product>> =
        try {
            val response = service.getSliderProducts()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(Constants.ErrorMessages.SLIDER_PRODUCTS_LOAD)
        }

    suspend fun getProductDetail(id: Int): Result<Product> =
        try {
            val response = service.getProductDetail(id)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(Constants.ErrorMessages.PRODUCT_DETAILS_LOAD)
        }
}