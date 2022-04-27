package com.kerubyte.feature.product.repository

import com.kerubyte.common.util.RootResponse
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.product.ProductConstants.CONTENT_NOT_FOUND
import com.kerubyte.feature.product.ProductConstants.INCORRECT_BODY
import com.kerubyte.feature.product.SearchRequest
import com.kerubyte.feature.product.service.ProductApiService
import io.ktor.http.*
import org.litote.kmongo.Id

class ProductRepositoryImpl(
    private val productApiService: ProductApiService
) : ProductRepository {
    override suspend fun getAllProducts(): Pair<HttpStatusCode, RootResponse<Any>> {

        val response = productApiService.getAllProducts()

        if (response.isEmpty()) {
            return Pair(
                HttpStatusCode.NotFound,
                RootResponse.ErrorResponse(
                    message = CONTENT_NOT_FOUND
                )
            )
        }
        return Pair(
            HttpStatusCode.OK,
            RootResponse.SuccessResponse(
                data = response
            )
        )
    }

    override suspend fun getProductById(input: Id<Product>?): Pair<HttpStatusCode, RootResponse<Any>> {
        val response = input?.let {
            productApiService.getProductById(it)
        } ?: return Pair(
            HttpStatusCode.NotFound,
            RootResponse.ErrorResponse(
                message = CONTENT_NOT_FOUND
            )
        )
        return Pair(
            HttpStatusCode.OK,
            RootResponse.SuccessResponse(
                data = response
            )
        )
    }

    override suspend fun searchProductsById(input: SearchRequest?): Pair<HttpStatusCode, RootResponse<Any>> {
        val response = input?.let { searchRequest ->
            if (!searchRequest.isValidRequest()) {
                return Pair(
                    HttpStatusCode.BadRequest,
                    RootResponse.ErrorResponse(
                        message = INCORRECT_BODY
                    )
                )
            }
            productApiService.searchProductsById(searchRequest.data!!)
        } ?: return Pair(
            HttpStatusCode.BadRequest,
            RootResponse.ErrorResponse(
                message = INCORRECT_BODY
            )
        )
        if (response.isEmpty()) {
            return Pair(
                HttpStatusCode.NotFound,
                RootResponse.ErrorResponse(
                    message = CONTENT_NOT_FOUND
                )
            )
        }
        return Pair(
            HttpStatusCode.OK,
            RootResponse.SuccessResponse(
                data = response
            )
        )
    }
}