package com.olx.iris

import akka.actor.{Actor, ActorLogging, Props}
import com.olx.iris.TransactionActor.{CreateTransaction, GetTransactionById}
import com.olx.iris.model.{Integrator, Transaction}
import com.olx.iris.mongodb.Mongo
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.bson.ObjectId

object TransactionActor {
  final case class CreateTransaction(transaction: Transaction, integrator: Integrator)
  final case class GetTransactionById(transactionId: String, integrator: Integrator)
  final case class GetTransactionByReference(transactionReference: String, integrator: Integrator)

  def props: Props = Props()
}

class TransactionActor(db: MongoDatabase) extends Actor with ActorLogging {

  val TX_COLLECTION: String = "transactions"

  def createTransaction(db: MongoDatabase, tx: Transaction, int: Integrator)=
    Mongo.transactionCollection.insertOne(tx)

  def getTransaction(txId: String, int: Integrator) =
    Mongo.transactionCollection.find(txId)()

  override def receive: Receive = {
    case CreateTransaction(tx: Transaction, int: Integrator) =>
      sender() ! createTransaction(db, tx, int)
    case GetTransactionById(txId: String, int: Integrator) =>
      sender() ! getTransaction(txId, int)
  }
}
