package com.kerubyte.feature.product

object ProductConstants {

    const val PRODUCTS = "/api/products"
    const val PRODUCT_BY_ID = "/api/products/{id}"
    const val SEARCH_PRODUCTS_BY_ID = "/api/products/search"

    // Error Constants for ProductRepository
    const val CONTENT_NOT_FOUND = "Requested content not found"
    const val INCORRECT_BODY = "Could not parse request payload"
}