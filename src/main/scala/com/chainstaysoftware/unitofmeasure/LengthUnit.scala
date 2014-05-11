package com.chainstaysoftware.unitofmeasure


case object Meter extends LengthUnit(LengthConstants.MeterLabel,
  LengthConstants.MeterAbbrev, 1.0)
case object Mile extends LengthUnit(LengthConstants.MileLabel,
  LengthConstants.MileAbbrev, LengthConstants.MeterPerMile)
case object Yard extends LengthUnit(LengthConstants.YARD_LABEL,
  LengthConstants.YardAbbrev, LengthConstants.MeterPerYard)
case object Foot extends LengthUnit(LengthConstants.FootLabel,
  LengthConstants.FootAbbrev, LengthConstants.MeterPerFoot)
case object Inch extends LengthUnit(LengthConstants.InchLabel,
  LengthConstants.InchAbbrev, LengthConstants.MeterPerIn)

sealed abstract class LengthUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Length)
