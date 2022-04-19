package com.kerubyte.common.database

import com.kerubyte.feature.product.Product
import com.kerubyte.feature.user.User
import com.mongodb.client.MongoCollection

interface DatabaseProvider {

    suspend fun provideProductCollection(): MongoCollection<Product>

    suspend fun provideUserCollection(): MongoCollection<User>
}