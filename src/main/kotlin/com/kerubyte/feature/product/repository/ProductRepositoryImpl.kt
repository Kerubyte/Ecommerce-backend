package com.kerubyte.feature.product.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.ProductConstants.CONTENT_NOT_FOUND
import com.kerubyte.feature.product.ProductConstants.INCORRECT_BODY
import com.kerubyte.feature.product.service.ProductApiService
import io.ktor.http.*
import org.litote.kmongo.Id

class ProductRepositoryImpl(
    private val productApiService: ProductApiService
) : ProductRepository {

    override suspend fun getAllProducts(): RootResponse<Any> {

        val response = productApiService.getAllProducts()

        if (response.isEmpty()) {
            return RootResponse.ErrorResponse(
                HttpStatusCode.NotFound,
                CONTENT_NOT_FOUND
            )
        }
        return RootResponse.SuccessResponse(
            HttpStatusCode.OK,
            response
        )
    }

    override suspend fun getProductById(input: Id<Product>?): RootResponse<Any> {
        val response = input?.let {
            productApiService.getProductById(it)
        } ?: return RootResponse.ErrorResponse(
            HttpStatusCode.NotFound,
            CONTENT_NOT_FOUND
        )
        return RootResponse.SuccessResponse(
            HttpStatusCode.OK,
            response
        )
    }

    override suspend fun searchProductsById(input: List<Id<Product>>?): RootResponse<Any> {
        val response = input?.let {
            productApiService.searchProductsById(it)
        } ?: return RootResponse.ErrorResponse(
            HttpStatusCode.BadRequest,
            INCORRECT_BODY
        )
        if (response.isEmpty()) {
            return RootResponse.ErrorResponse(HttpStatusCode.NotFound)
        }
        return RootResponse.SuccessResponse(
            HttpStatusCode.OK,
            response
        )
    }
}