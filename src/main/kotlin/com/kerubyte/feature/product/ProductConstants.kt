package com.kerubyte.feature.product

object ProductConstants {

    const val PRODUCTS = "/products"
    const val PRODUCT_BY_ID = "/products/{id}"
    const val SEARCH_PRODUCTS_BY_ID = "/products/search"

    // Error Constants for ProductRepository
    const val CONTENT_NOT_FOUND = "Requested content not found"
    const val INCORRECT_BODY = "Could not parse request payload"
}