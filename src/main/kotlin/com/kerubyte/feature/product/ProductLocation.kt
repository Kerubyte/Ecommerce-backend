package com.kerubyte.feature.product

import com.kerubyte.feature.product.ProductConstants.PRODUCTS
import com.kerubyte.feature.product.ProductConstants.PRODUCT_BY_ID
import com.kerubyte.feature.product.ProductConstants.SEARCH_PRODUCTS_BY_ID
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(PRODUCTS)
class Products

@KtorExperimentalLocationsAPI
@Location(PRODUCT_BY_ID)
data class ProductById(val id: String)

@KtorExperimentalLocationsAPI
@Location(SEARCH_PRODUCTS_BY_ID)
class SearchProductById