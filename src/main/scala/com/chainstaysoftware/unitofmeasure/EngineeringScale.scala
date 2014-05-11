package com.chainstaysoftware.unitofmeasure


case object Atto extends EngineeringScale("atto", "a", 1E-18)
case object Femto extends EngineeringScale("femto", "f", 1E-15)
case object Pico extends EngineeringScale("pico", "p", 1E-12)
case object Nano extends EngineeringScale("nano", "n", 1E-9)
// unicode 03bc is a lower case Greek mu
case object Micro extends EngineeringScale("micro", "\u03bc", 1E-6)
case object Milli extends EngineeringScale("milli", "m", 1E-3)
case object None extends EngineeringScale("none", "", 1.0)
case object Kilo extends EngineeringScale("kilo", "K", 1E3)
case object Mega extends EngineeringScale("mega", "M", 1E6)
case object Giga extends EngineeringScale("giga", "G", 1E9)
case object Tera extends EngineeringScale("tera", "T", 1E12)
case object Peta extends EngineeringScale("peta", "P", 1E15)
case object Exa extends EngineeringScale("exa", "E", 1E18)


sealed abstract class EngineeringScale(val prefix: String, val symbol: String, val factor: Double) {
  def convertToUnscaled(scaledValue: Double) = scaledValue * factor

  def convertToScaled(unscaledVale: Double) = unscaledVale / factor

  def getScale(raw: Double): EngineeringScale = {
    getScale(raw, scaleValues.head, scaleValues.tail)
  }

  private def getScale(raw: Double, head: EngineeringScale, tail: List[EngineeringScale]) : EngineeringScale = {
    if (tail == Nil || Math.abs(raw) < tail.head.factor)
      head
    else
      getScale(raw, tail.head, tail.tail)
  }

  private def scaleValues: List[EngineeringScale] = List(Atto, Femto, Pico, Nano,
    Micro, Milli, None, Kilo, Mega, Giga, Tera, Peta, Exa)
}
