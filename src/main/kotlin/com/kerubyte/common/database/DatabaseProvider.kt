package com.kerubyte.common.database

import com.kerubyte.common.util.CommonConstants.COLLECTION_PRODUCT
import com.kerubyte.common.util.CommonConstants.COLLECTION_USER
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.user.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class DatabaseProvider(
    private val kmongoDatabase: CoroutineDatabase
) {

    fun provideProductCollection(): CoroutineCollection<Product> {
        return kmongoDatabase.getCollection(COLLECTION_PRODUCT)
    }

    fun provideUserCollection(): CoroutineCollection<User> {
        return kmongoDatabase.getCollection(COLLECTION_USER)
    }
}