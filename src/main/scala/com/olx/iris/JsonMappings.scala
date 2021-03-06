package com.olx.iris

import java.time.ZonedDateTime

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.olx.iris.model._
import com.olx.iris.TransactionActor.TransactionCreated
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

trait JsonMappings extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit object ZonedDateTimeJsonFormat extends RootJsonFormat[ZonedDateTime] {
    override def write(obj: ZonedDateTime) = JsString(obj.toString)

    override def read(json: JsValue): ZonedDateTime = json match {
      case JsString(s) => ZonedDateTime.parse(s)
      case _           => throw new DeserializationException("Error info you want here ...")
    }
  }

  implicit val addressFormat = jsonFormat6(Address)
  implicit val amountFormat = jsonFormat2(MonetaryAmount)
  implicit val vatDataFormat = jsonFormat2(VatData)
  implicit val customerFormat = jsonFormat9(Customer)
  implicit val paymentReferenceFormat = jsonFormat2(PaymentReference)
  implicit val productFormat = jsonFormat11(Item)
  implicit val transactionFormat = jsonFormat4(Transaction)
  implicit val transactionCreatedFormat = jsonFormat1(TransactionCreated)
}
