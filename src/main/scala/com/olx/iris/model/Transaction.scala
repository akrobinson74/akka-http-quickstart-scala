package com.olx.iris.model

import java.time.ZonedDateTime

import io.circe.{Encoder, Json}
import io.circe.syntax._
import org.mongodb.scala.bson.ObjectId

class InvoiceType extends Enumeration {
  type InvoiceType = Value
  val CREDIT_NOTE, INVOICE = Value
}

case class Invoice(
  invoiceNumber: String,
  invoiceLocation: String,
  invoiceType: InvoiceType,
  notified: Boolean,
  transactionId: String
)

case class Customer(
  address: Address,
  businessName: Option[String],
  customerType: String,
  emailAddress: String,
  firstName: String,
  language: String,
  lastName: String,
  userId: String,
  vatData: VatData
)

case class Address(
  city: String,
  country: String,
  houseNumber: String,
  region: String,
  street: String,
  zipCode: String
)

case class VatData(
  applyVat: Boolean = false,
  vatNumber: Option[String]
)

case class PaymentReference(
  amount: MonetaryAmount,
  executionTime: ZonedDateTime
)

case class MonetaryAmount(
  currency: String,
  amount: BigDecimal
)

case class Item(
  currency: String = "CRD",
  description: String,
  discountAmount: Option[BigDecimal],
  expiryDateTime: Option[ZonedDateTime],
  grossPrice: BigDecimal = BigDecimal(0.00),
  netPrice: Option[BigDecimal],
  productType: String,
  unitPrice: BigDecimal = BigDecimal(1.00),
  units: BigInt,
  vatAmount: Option[BigDecimal],
  vatPercentage: BigDecimal = BigDecimal(0.00)
)

case class Transaction(
  customer: Customer,
  paymentReference: PaymentReference,
  items: List[Item],
  transactionReference: String
)

//object Transaction {
//  implicit val encoder: Encoder[Transaction] = (obj: Transaction) => {
//    Json.obj(
//      "id" -> obj._id.toHexString.asJson,
//      "customer" -> obj.customer.asJson
//    )
//  }
//}