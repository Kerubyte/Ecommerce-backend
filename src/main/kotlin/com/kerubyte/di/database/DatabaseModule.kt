package com.kerubyte.di.database

import com.kerubyte.common.database.DatabaseProvider
import com.kerubyte.common.util.CommonConstants.DB_NAME
import com.kerubyte.feature.product.Product
import com.kerubyte.feature.user.User
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseModule = module {

    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(DB_NAME)
    }

    single {
        DatabaseProvider(get())
    }
}