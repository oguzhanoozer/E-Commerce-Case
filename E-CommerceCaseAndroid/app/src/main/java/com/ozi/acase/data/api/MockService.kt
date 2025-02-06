package com.ozi.acase.service.mock

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.service.base.BaseService

class MockService : BaseService {
    override suspend fun getProducts(): List<Product> = MockData.products
    override suspend fun getProductDetail(id: Int): Product = MockData.productDetail
    override suspend fun getSliderProducts(): List<Product> = MockData.products.take(5)
}