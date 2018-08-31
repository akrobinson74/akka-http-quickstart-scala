package com.olx.iris

import akka.actor.{Actor, ActorLogging, Props}
import com.olx.iris.TransactionActor.CreateTransaction

object TransactionActor {
  final case class CreateTransaction(transaction: Transaction, integrator: Integrator)
  final case class GetTransactionById(transactionId: String, integrator: Integrator)
  final case class GetTransactionByReference(transactionReference: String, integrator: Integrator)

  def props: Props = Props()
}

class TransactionActor() extends Actor with ActorLogging {

  override def receive: Receive = {
    case CreateTransaction =>
//      sender() !
  }
}
