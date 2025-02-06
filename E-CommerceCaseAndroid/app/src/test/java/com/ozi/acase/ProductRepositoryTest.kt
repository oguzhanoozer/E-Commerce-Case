package com.ozi.acase

import com.ozi.acase.data.repository.ProductRepository
import com.ozi.acase.data.service.base.BaseService
import com.ozi.acase.service.mock.MockData
import com.ozi.acase.service.mock.MockService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryTest {

    private lateinit var repository: ProductRepository
    private lateinit var mockService: BaseService

    @Before
    fun setup() {
        mockService = MockService()
        repository = ProductRepository(mockService)
    }

    @Test
    fun `test get all products`() = runTest {
        // When
        val result = repository.getAllProducts()

        // Then
        assertNotNull(result)
        assertEquals(MockData.products.size, result.size)
        assertEquals(MockData.products[0].id, result[0].id)
        assertEquals(MockData.products[0].title, result[0].title)
    }

    @Test
    fun `test get slider products`() = runTest {
        // When
        val result = repository.getSliderProducts()

        // Then
        assertNotNull(result)
        assertEquals(5, result.size)
    }

    @Test
    fun `test get product detail`() = runTest {
        // Given
        val productId = 5

        // When
        val result = repository.getProductDetail(productId)

        // Then
        assertNotNull(result)
        assertEquals(MockData.productDetail.id, result.id)
    }
}