package com.ozi.acase

import com.ozi.acase.data.model.Product
import com.ozi.acase.data.repository.ProductRepository
import com.ozi.acase.data.service.base.BaseService
import com.ozi.acase.service.mock.MockData
import com.ozi.acase.service.mock.MockService
import com.ozi.acase.utils.Constants
import com.ozi.acase.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

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
        val result = repository.getProducts(1, 5)

        assertNotNull(result)
        when (result) {
            is Result.Success -> {
                val products: List<Product>? = result.data
                if (products != null) {
                    assertEquals(5, products.size)
                    assertEquals(MockData.products[0].id, products[0].id)
                    assertEquals(MockData.products[0].title, products[0].title)
                }
            }
            is Result.Error -> fail("Should not be error")
        }
    }

    @Test
    fun `test get products second page`() = runTest {
        val result = repository.getProducts(2, 5)

        assertNotNull(result)
        when (result) {
            is Result.Success -> {
                val products: List<Product>? = result.data
                if (products != null) {
                    assertEquals(5, products.size)
                    assertEquals(MockData.products[1].id, products[1].id)
                }
            }
            is Result.Error -> fail("Should not be error")
        }
    }

    @Test
    fun `test get slider products`() = runTest {
        val result = repository.getSliderProducts()

        assertNotNull(result)
        when (result) {
            is Result.Success -> {
                val products: List<Product>? = result.data
                if (products != null) {
                    assertEquals(5, products.size)
                }
            }
            is Result.Error -> fail("Should not be error")
        }
    }

    @Test
    fun `test get product detail`() = runTest {
        val productId = 5

        val result = repository.getProductDetail(productId)

        assertNotNull(result)
        when (result) {
            is Result.Success -> {
                val product: Product? = result.data
                if (product != null) {
                    assertEquals(MockData.productDetail.id, product.id)
                }
            }
            is Result.Error -> fail("Should not be error")
        }
    }

    @Test
    fun `test get products error`() = runTest {
        mockService = MockService(shouldFail = true)
        repository = ProductRepository(mockService)

        val result = repository.getProducts(1, 5)

        assertTrue(result is Result.Error)
        assertEquals(Constants.ErrorMessages.PRODUCTS_LOAD, (result as Result.Error).message)
    }

    @Test
    fun `test get slider products error`() = runTest {
        mockService = MockService(shouldFail = true)
        repository = ProductRepository(mockService)

        val result = repository.getSliderProducts()

        assertTrue(result is Result.Error)
        assertEquals(Constants.ErrorMessages.SLIDER_PRODUCTS_LOAD, (result as Result.Error).message)
    }

    @Test
    fun `test get product detail error`() = runTest {
        mockService = MockService(shouldFail = true)
        repository = ProductRepository(mockService)

        val result = repository.getProductDetail(5)

        assertTrue(result is Result.Error)
        assertEquals(Constants.ErrorMessages.PRODUCT_DETAILS_LOAD, (result as Result.Error).message)
    }
}