package com.chainstaysoftware.unitofmeasure

object LengthConstants {
  val METER_LABEL: String = "meters"
  val METER_ABBREV: String = "m"
  val FOOT_LABEL: String = "feet"
  val FOOT_ABBREV: String = "ft"
  val INCH_LABEL: String = "inches"
  val INCH_ABBREV: String = "in"

  val CM_PER_IN: Double = 2.54
  val IN_PER_FOOT: Double = 12.0
  val CM_PER_METER: Double = 100.0
  val METER_PER_FOOT: Double = IN_PER_FOOT * CM_PER_IN / CM_PER_METER
  val METER_PER_IN: Double = CM_PER_IN / CM_PER_METER
}
