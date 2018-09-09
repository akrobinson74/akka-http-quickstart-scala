package com.olx.iris.mongodb

import com.olx.iris.model.{ Address, Customer, Integrator, Invoice, Transaction }
import com.typesafe.config.ConfigFactory
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistries.{ fromProviders, fromRegistries }
import org.mongodb.scala.{ MongoClient, MongoCollection, MongoDatabase }
import org.mongodb.scala.bson.codecs.{ DEFAULT_CODEC_REGISTRY, Macros }

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

trait Mongo {
  val providersList: java.util.List[CodecProvider] = ArrayBuffer(
    Macros.createCodecProvider(classOf[Address]),
    Macros.createCodecProvider(classOf[Customer]),
    Macros.createCodecProvider(classOf[Invoice]),
    Macros.createCodecProvider(classOf[Integrator]),
    Macros.createCodecProvider(classOf[Transaction])
  ).asJava

  lazy val config = ConfigFactory.load()
  lazy val mongoClient: MongoClient = MongoClient(config.getString("mongo.uri"))
  lazy val codecRegistry = fromRegistries(fromProviders(providersList), DEFAULT_CODEC_REGISTRY)
  lazy val database: MongoDatabase =
    mongoClient.getDatabase(config.getString("mongo.database")).withCodecRegistry(codecRegistry)

  lazy val addressCollection: MongoCollection[Address] = database.getCollection[Address]("addresses")
  lazy val customerCollection: MongoCollection[Customer] = database.getCollection[Customer]("customers")
  lazy val integratorCollection: MongoCollection[Integrator] = database.getCollection[Integrator]("integrators")
  lazy val invoiceCollection: MongoCollection[Invoice] = database.getCollection[Invoice]("invoices")
  lazy val transactionCollection: MongoCollection[Transaction] =
    database.getCollection[Transaction]("transactions")
}
