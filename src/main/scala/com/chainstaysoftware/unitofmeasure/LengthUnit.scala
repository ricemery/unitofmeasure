package com.chainstaysoftware.unitofmeasure


case object METER extends LengthUnit(LengthConstants.METER_LABEL,
  LengthConstants.METER_ABBREV, 1.0)
case object FOOT extends LengthUnit(LengthConstants.FOOT_LABEL,
  LengthConstants.FOOT_ABBREV, LengthConstants.METER_PER_FOOT)
case object INCH extends LengthUnit(LengthConstants.INCH_LABEL,
  LengthConstants.INCH_ABBREV, LengthConstants.METER_PER_IN)

sealed abstract class LengthUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, LENGTH)
