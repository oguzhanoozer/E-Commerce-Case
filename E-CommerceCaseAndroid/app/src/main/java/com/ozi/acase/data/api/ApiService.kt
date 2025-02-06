// ApiService.kt
package com.ozi.acase.data.api

import com.ozi.acase.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @GET("products")
    suspend fun getSliderProducts(@Query("limit") limit: Int = 5): List<Product>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): Product
}