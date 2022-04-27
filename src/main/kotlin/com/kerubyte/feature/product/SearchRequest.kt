package com.kerubyte.feature.product

import org.litote.kmongo.Id


data class SearchRequest(
    val data: List<Id<Product>>?
) {
    fun isValidRequest(): Boolean {
        return true
    }
}
