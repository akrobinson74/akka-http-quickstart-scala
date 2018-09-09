package com.olx.iris.model

import java.util.{Currency, Locale}

class Integrator extends Enumeration {

  protected case class Val(
    bankAccountId: String,
    currency: Currency,
    locales: List[Locale]
  ) extends super.Val

  val OLX_PK = Val(
    "NEDSZAJJXXX",
    Currency.getInstance("PKR"),
    List(new Locale("en", "PK"), new Locale("ur", "PK"))
  )
  val OLX_ZA = Val(
    "ABSA_40-8989-1591",
    Currency.getInstance("ZAR"),
    List(new Locale("en", "ZA"))
  )
}
