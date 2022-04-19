package com.kerubyte.testUtil

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class FlapdoodleRule(
    val dbName: String = "test-db"
) : TestRule {

    val mongoClient: MongoClient by lazy {
        FlapdoodleConfig.mongoClient
    }

    val database: MongoDatabase by lazy {
        FlapdoodleConfig.getDatabase()
    }

    fun populateDB(collectionName: String, document: Document) {
        database.getCollection(collectionName).insertOne(document)
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            override fun evaluate() {
                try {
                    base.evaluate()
                } finally {
                    mongoClient.getDatabase(dbName).drop()
                }
            }
        }
    }
}