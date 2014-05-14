package com.chainstaysoftware.unitofmeasure

case class Precision(p: Double)

object EqualsHelper {
  implicit class DoubleWithEpsilonEquals(val d: Double) extends AnyVal {
    def ~=(d2:Double)(implicit p:Precision) = (d - d2).abs < p.p
  }
}
