package com.kerubyte.di.database

import com.kerubyte.common.database.DatabaseProvider
import com.kerubyte.common.database.DatabaseProviderImpl
import com.kerubyte.common.util.CommonConstants.DB_NAME
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<MongoDatabase> {
        MongoClients.create()
            .getDatabase(DB_NAME)
    }

    single<DatabaseProvider> {
        DatabaseProviderImpl(get())
    }
}