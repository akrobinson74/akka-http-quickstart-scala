package com.olx.iris

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.olx.iris.model.Transaction
import com.olx.iris.mongodb.Mongo

trait Iris extends Mongo {
  val routes: Route = {
    pathPrefix("orders") {
      path(Segment) { transactionReference =>
        get {
          entity(as [Transaction]) {

          }
        }
      }
    }
  }
}

object IrisService {

}
