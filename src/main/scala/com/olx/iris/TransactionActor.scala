package com.olx.iris

import akka.actor.{ Actor, ActorLogging, Props }
import com.olx.iris.TransactionActor.{ CreateTransaction, GetTransactionById, GetTransactionByReference }
import com.olx.iris.model.{ Integrator, Transaction }
import com.olx.iris.mongodb.Mongo
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.model.Filters._

object TransactionActor {
  final case class CreateTransaction(transaction: Transaction)
  final case class GetTransactionById(transactionId: String, integrator: Integrator)
  final case class GetTransactionByReference(transactionReference: String, integrator: Integrator)

  def props: Props = Props()
}

class TransactionActor() extends Mongo with Actor with ActorLogging {

  val TX_COLLECTION: String = "transactions"

  def createTransaction(tx: Transaction) =
    transactionCollection.insertOne(tx)

  def getTransactionById(id: String, integrator: Integrator) =
    transactionCollection.find(
      and(equal("_id", id), equal("integrator", integrator))
    )

  def getTransactionByReference(transactionReference: String, int: Integrator) =
    transactionCollection.find(
      and(equal("transactionReference", transactionReference), equal("integrator", int))
    )

  override def receive: Receive = {
    case CreateTransaction(transaction: Transaction) =>
      sender() ! createTransaction(transaction)
    case GetTransactionById(id: String, integrator: Integrator) =>
      sender() ! getTransactionById(id, integrator)
    case GetTransactionByReference(transactionReference: String, integrator: Integrator) =>
      sender() ! getTransactionByReference(transactionReference, integrator)
  }
}
