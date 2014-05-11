package com.chainstaysoftware.unitofmeasure

object LengthConstants {
  val MeterLabel = "meters"
  val MeterAbbrev = "m"
  val MileLabel = "miles"
  val MileAbbrev = "mi"
  val YARD_LABEL = "yard"
  val YardAbbrev = "yd"
  val FootLabel = "feet"
  val FootAbbrev = "ft"
  val InchLabel = "inches"
  val InchAbbrev = "in"

  val CM_PER_IN: Double = 2.54
  val IN_PER_FOOT: Double = 12.0
  val CM_PER_METER: Double = 100.0
  val MeterPerMile: Double = 1609.34
  val MeterPerYard: Double = CM_PER_IN / CM_PER_METER * 12 * 3
  val MeterPerFoot: Double = IN_PER_FOOT * CM_PER_IN / CM_PER_METER
  val MeterPerIn: Double = CM_PER_IN / CM_PER_METER
}
