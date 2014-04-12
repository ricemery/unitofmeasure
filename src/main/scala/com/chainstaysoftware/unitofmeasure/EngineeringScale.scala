package com.chainstaysoftware.unitofmeasure


case object ATTO extends EngineeringScale("atto", "a", 1E-18)
case object FEMTO extends EngineeringScale("femto", "f", 1E-15)
case object PICO extends EngineeringScale("pico", "p", 1E-12)
case object NANO extends EngineeringScale("nano", "n", 1E-9)
// unicode 03bc is a lower case Greek mu
case object MICRO extends EngineeringScale("micro", "\u03bc", 1E-6)
case object MILLI extends EngineeringScale("milli", "m", 1E-3)
case object NONE extends EngineeringScale("none", "", 1.0)
case object KILO extends EngineeringScale("kilo", "K", 1E3)
case object MEGA extends EngineeringScale("mega", "M", 1E6)
case object GIGA extends EngineeringScale("giga", "G", 1E9)
case object TERA extends EngineeringScale("tera", "T", 1E12)
case object PETA extends EngineeringScale("peta", "P", 1E15)
case object EXA extends EngineeringScale("exa", "E", 1E18)


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

  private def scaleValues: List[EngineeringScale] = List(ATTO, FEMTO, PICO, NANO,
    MICRO, MILLI, NONE, KILO, MEGA, GIGA, TERA, PETA, EXA)
}
