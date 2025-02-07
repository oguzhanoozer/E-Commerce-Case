package com.ozi.acase.service.mock

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.service.base.BaseService

class MockService(private val shouldFail: Boolean = false) : BaseService {
    override suspend fun getProducts(page: Int, limit: Int): List<Product> {
        if (shouldFail) throw Exception()
        return MockData.products
    }

    override suspend fun getSliderProducts(): List<Product> {
        if (shouldFail) throw Exception()
        return MockData.products
    }

    override suspend fun getProductDetail(id: Int): Product {
        if (shouldFail) throw Exception()
        return MockData.productDetail
    }
}