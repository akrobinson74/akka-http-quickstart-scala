package com.olx.iris

import akka.actor.{Actor, ActorLogging, Props}
import com.olx.iris.TransactionActor.CreateTransaction
import com.olx.iris.model.{Integrator, Transaction}
import org.mongodb.scala.MongoDatabase

object TransactionActor {
  final case class CreateTransaction(transaction: Transaction, integrator: Integrator)
  final case class GetTransactionById(transactionId: String, integrator: Integrator)
  final case class GetTransactionByReference(transactionReference: String, integrator: Integrator)

  def props: Props = Props()
}

class TransactionActor(db: MongoDatabase) extends Actor with ActorLogging {

  override def receive: Receive = {
    case CreateTransaction =>
//      sender() !
  }
}
