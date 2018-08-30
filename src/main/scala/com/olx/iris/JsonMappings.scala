package com.olx.iris

import java.time.ZonedDateTime

import com.olx.iris.CustomerType.CustomerType
import com.olx.iris.ProductType.ProductType
import spray.json.DefaultJsonProtocol

object CustomerType extends Enumeration {
  type CustomerType = Value
  val BUSINESS, RESIDENTIAL = Value
}

object ProductType extends Enumeration {
  type ProductType = Value
  val AD_UPGRADE = Value("CashPurchase")
  val EFFORT = Value("Reward")
  val LIMITS = Value("CashPurchase")
  val REWARD = Value("Reward")
  val TOPUP = Value("WalletTopUp")
}

case class Customer(
  address: Address,
  businessName: Option[String],
  customerType: CustomerType,
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

case class Product(
  currency: String = "CRD",
  description: String,
  discountAmount: Option[BigDecimal],
  expiryDateTime: Option[ZonedDateTime],
  grossPrice: BigDecimal = BigDecimal(0.00),
  netPrice: Option[BigDecimal],
  productType: ProductType,
  unitPrice: BigDecimal = BigDecimal(1.00),
  units: BigInt,
  vatAmount: Option[BigDecimal],
  vatPercentage: BigDecimal = BigDecimal(0.00)
)

case class Transaction(
  val customer: Customer,
  val paymentReference: PaymentReference,
  val products: List[Product],
  val transactionReference: String
)

trait JsonMappings extends DefaultJsonProtocol {
  implicit val addressFormat = jsonFormat6(Address)
  implicit val amountFormat = jsonFormat2(MonetaryAmount)
  implicit val customerTypeFormat = jsonFormat1(CustomerType)
  implicit val productTypeFormat = jsonFormat1(ProductType)
  implicit val vatDataFormat = jsonFormat2(VatData)
  implicit val customerFormat = jsonFormat9(Customer)
  implicit val paymentReferenceFormat = jsonFormat2(PaymentReference)
  implicit val productFormat = jsonFormat11(Product)
  implicit val transactionFormat = jsonFormat4(Transaction)
}
