package com.olx.iris

import java.util.concurrent.TimeUnit

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import com.olx.iris.model.{ Integrator, Transaction }
import com.olx.iris.mongodb.Mongo
import com.olx.iris.TransactionActor.{ CreateTransaction, GetTransactionByReference, TransactionCreated }

import scala.concurrent.{ ExecutionContext, Future }

trait Iris extends Mongo with JsonMappings {
  implicit def system: ActorSystem

  implicit lazy val timeout = Timeout(5, TimeUnit.SECONDS)

  implicit val executionContext: ExecutionContext

  implicit val integrator: Integrator

  def transactionActor: ActorRef

  val routes: Route = {
    pathPrefix("orders") {
      post {
        entity(as[Transaction]) { transaction =>
          val transactionCreated = (transactionActor ? CreateTransaction(transaction)).onComplete {
            case util.Success(value) =>
              complete((StatusCodes.Created, TransactionCreated(transaction.transactionReference)))
            case scala.util.Failure(exception) => complete((StatusCodes.InternalServerError))
          }

        }
      } ~
        path(Segment) { transactionRef: String =>
          get {
            val transaction: Future[Transaction] =
              (transactionActor ? GetTransactionByReference(transactionRef, integrator)).mapTo[Transaction]
            complete(transaction)
          }
        }
    }
  }
}

object IrisService {}
