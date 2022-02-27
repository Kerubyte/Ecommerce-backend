package com.kerubyte.feature.product

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Product(
    @BsonId
    val _id: Id<Product>,
    val name: String,
    val brand: String,
    val description: String,
    val category: String,
    val imageUrl: String,
    val price: Double
)
