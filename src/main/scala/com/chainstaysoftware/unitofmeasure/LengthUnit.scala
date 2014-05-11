package com.chainstaysoftware.unitofmeasure


case object Meter extends LengthUnit(LengthConstants.MeterLabel,
  LengthConstants.MeterAbbrev, 1.0)
case object MILE extends LengthUnit(LengthConstants.MileLabel,
  LengthConstants.MileAbbrev, LengthConstants.MeterPerMile)
case object YARD extends LengthUnit(LengthConstants.YARD_LABEL,
  LengthConstants.YardAbbrev, LengthConstants.MeterPerYard)
case object FOOT extends LengthUnit(LengthConstants.FootLabel,
  LengthConstants.FootAbbrev, LengthConstants.MeterPerFoot)
case object INCH extends LengthUnit(LengthConstants.InchLabel,
  LengthConstants.InchAbbrev, LengthConstants.MeterPerIn)

sealed abstract class LengthUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Length)
