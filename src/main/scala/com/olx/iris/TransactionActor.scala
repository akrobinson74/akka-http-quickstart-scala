package com.olx.iris

import akka.actor.{Actor, ActorLogging, Props}
import com.mongodb.async.client.Observer
import com.olx.iris.TransactionActor.{CreateTransaction, GetTransactionById, GetTransactionByReference, TransactionCreated}
import com.olx.iris.model.{Integrator, Transaction}
import com.olx.iris.mongodb.Mongo
import org.mongodb.scala.model.Filters._

import scala.concurrent.Future

object TransactionActor {
  final case class CreateTransaction(transaction: Transaction)
  final case class GetTransactionById(transactionId: String, integrator: Integrator)
  final case class GetTransactionByReference(transactionReference: String, integrator: Integrator)
  final case class TransactionCreated(transactionReference: String)

  def props: Props = Props()
}

class TransactionActor() extends Mongo with Actor with ActorLogging {

  def createTransaction(tx: Transaction) =
    transactionCollection
      .insertOne(tx)
      .toFuture()

  def getTransactionById(id: String, integrator: Integrator) =
    transactionCollection
      .find(
        and(equal("_id", id), equal("integrator", integrator))
      )
      .first()
      .toFuture()

  def getTransactionByReference(transactionReference: String, integrator: Integrator) =
    transactionCollection
      .find(
        and(equal("transactionReference", transactionReference), equal("integrator", integrator))
      )
      .first()
      .toFuture()

  override def receive: Receive = {
    case CreateTransaction(transaction: Transaction) =>
      sender() ! createTransaction(transaction)
    case GetTransactionById(id: String, integrator: Integrator) =>
      sender() ! getTransactionById(id, integrator)
    case GetTransactionByReference(transactionReference: String, integrator: Integrator) =>
      sender() ! getTransactionByReference(transactionReference, integrator)
  }
}
