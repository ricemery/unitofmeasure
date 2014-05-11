package com.chainstaysoftware.unitofmeasure

case object Radians extends AngleUnit(AngleConstants.RadiansLabel,
  AngleConstants.RadiansAbbrev, 1.0)
case object Degrees extends AngleUnit(AngleConstants.DegreesLabel,
  AngleConstants.DegreesAbbrev, AngleConstants.RadianPerDegree)

sealed abstract class AngleUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Angle)
