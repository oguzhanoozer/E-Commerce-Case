package com.ozi.acase.data.service.api

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.service.base.BaseService
import com.ozi.acase.utils.Constants.Api
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService : BaseService {
    @GET(Api.PRODUCTS)
    override suspend fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<Product>
    @GET(Api.PRODUCT_DETAIL)
    override suspend fun getProductDetail(@Path("id") id: Int): Product

    @GET(Api.SLIDER_PRODUCTS)
    override suspend fun getSliderProducts(): List<Product>
}