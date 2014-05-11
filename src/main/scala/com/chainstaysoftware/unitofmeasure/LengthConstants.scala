package com.chainstaysoftware.unitofmeasure

object LengthConstants {
  val METER_LABEL = "meters"
  val METER_ABBREV = "m"
  val MILE_LABEL = "miles"
  val MILE_ABBREV = "mi"
  val YARD_LABEL = "yard"
  val YARD_ABBREV = "yd"
  val FOOT_LABEL = "feet"
  val FOOT_ABBREV = "ft"
  val INCH_LABEL = "inches"
  val INCH_ABBREV = "in"

  val CM_PER_IN: Double = 2.54
  val IN_PER_FOOT: Double = 12.0
  val CM_PER_METER: Double = 100.0
  val METER_PER_MILE: Double = 1609.34
  val METER_PER_YARD: Double = CM_PER_IN / CM_PER_METER * 12 * 3
  val METER_PER_FOOT: Double = IN_PER_FOOT * CM_PER_IN / CM_PER_METER
  val METER_PER_IN: Double = CM_PER_IN / CM_PER_METER
}
