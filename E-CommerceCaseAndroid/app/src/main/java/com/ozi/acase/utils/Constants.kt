package com.ozi.acase.utils

object Constants {
    object Api {
        const val BASE_URL = "https://fakestoreapi.com/"
        const val PRODUCTS = "products"
        const val PRODUCT_DETAIL = "products/{id}"
        const val SLIDER_PRODUCTS = "products?limit=5"
    }
    object ErrorMessages {
        const val PRODUCTS_LOAD = "Could not load products. Please try again."
        const val PRODUCT_DETAILS_LOAD = "Could not load product details. Please try again."
        const val SLIDER_PRODUCTS_LOAD = "Could not load slider products. Please try again."
        const val GENERAL_ERROR = "An unexpected error occurred"
    }

    object Dialog {
        const val ERROR_TITLE = "Error"
        const val BUTTON_OK = "OK"
        const val BUTTON_CLOSE = "Close"
    }
}