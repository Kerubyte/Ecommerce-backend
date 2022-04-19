package com.kerubyte.testUtil

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig
import de.flapdoodle.embed.mongo.config.MongodConfig
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.bson.BsonDocument
import org.bson.Document
import org.litote.kmongo.service.MongoClientProvider

object FlapdoodleConfig {

    private val MongodProcess.host get() = "127.0.0.1:${config.net().port}"

    private val connectionString: ConnectionString by lazy {
        connectionString { host, command, callback ->
            try {
                callback(
                    MongoClientProvider
                        .createMongoClient<MongoClient>(ConnectionString("mongodb://$host"))
                        .getDatabase("test-db")
                        .runCommand(command),
                    null
                )
            } catch (e: Exception) {
                callback(null, e)
            }
        }
    }

    val mongoClient: MongoClient by lazy { newMongoClient() }
    private var port = Network.getFreeServerPort()
    private var config: ImmutableMongodConfig = MongodConfig.builder()
        .version(Version.Main.PRODUCTION)
        .net(Net(port, Network.localhostIsIPv6()))
        .build()

    private val mongodProcess: MongodProcess by lazy {
        createInstance()
    }

    private fun newMongoClient(): MongoClient = MongoClientProvider.createMongoClient(connectionString)

    private fun connectionString(commandExecutor: (String, BsonDocument, (Document?, Throwable?) -> Unit) -> Unit): ConnectionString =
        ConnectionString(
            "mongodb://${mongodProcess.host}"
        )

    private fun createInstance(): MongodProcess {
        return MongodStarter.getDefaultInstance().prepare(config).start()
    }

    fun getDatabase(dbName: String = "test-db"): MongoDatabase = mongoClient.getDatabase(dbName)
}