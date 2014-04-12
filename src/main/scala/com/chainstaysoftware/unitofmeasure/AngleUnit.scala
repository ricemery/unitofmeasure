package com.chainstaysoftware.unitofmeasure

case object RADIANS extends AngleUnit(AngleConstants.RADIANS_LABEL,
  AngleConstants.RADIANS_ABBREV, 1.0)
case object DEGREES extends AngleUnit(AngleConstants.DEGREES_LABEL,
  AngleConstants.DEGREES_ABBREV, AngleConstants.RADIAN_PER_DEGREE)

sealed abstract class AngleUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, ANGLE)
