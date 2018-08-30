package com.olx.iris

import java.time.ZonedDateTime
import spray.json.{ DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat }

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

trait JsonMappings extends DefaultJsonProtocol {

  implicit object ZonedDateTimeJsonFormat extends RootJsonFormat[ZonedDateTime] {

    override def write(obj: ZonedDateTime) = JsString(obj.toString)

    override def read(json: JsValue): ZonedDateTime = json match {
      case JsString(s) => ZonedDateTime.parse(s)
      case _ => throw new DeserializationException("Error info you want here ...")
    }
  }

  implicit val addressFormat = jsonFormat6(Address)
  implicit val amountFormat = jsonFormat2(MonetaryAmount)
  implicit val vatDataFormat = jsonFormat2(VatData)
  implicit val customerFormat = jsonFormat9(Customer)
  implicit val paymentReferenceFormat = jsonFormat2(PaymentReference)
  implicit val productFormat = jsonFormat11(Item)
  implicit val transactionFormat = jsonFormat4(Transaction)
}
