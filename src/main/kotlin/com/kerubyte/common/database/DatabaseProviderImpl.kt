package com.kerubyte.common.database

import com.kerubyte.common.util.CommonConstants.COLLECTION_PRODUCT
import com.kerubyte.common.util.CommonConstants.COLLECTION_USER
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.user.User
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.withKMongo

class DatabaseProviderImpl(
    private val mongoDatabase: MongoDatabase
) : DatabaseProvider {

    override suspend fun provideProductCollection(): MongoCollection<Product> {
        return mongoDatabase.withKMongo().getCollection(COLLECTION_PRODUCT, Product::class.java)
    }

    override suspend fun provideUserCollection(): MongoCollection<User> {
        return mongoDatabase.withKMongo().getCollection(COLLECTION_USER, User::class.java)
    }
}