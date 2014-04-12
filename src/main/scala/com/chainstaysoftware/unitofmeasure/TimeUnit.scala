package com.chainstaysoftware.unitofmeasure

case object SECOND extends TimeUnit(TimeConstants.SECOND_LABEL,
  TimeConstants.SECOND_ABBREV, 1.0)
case object MINUTE extends TimeUnit(TimeConstants.MINUTE_LABEL,
  TimeConstants.MINUTE_ABBREV, TimeConstants.SEC_PER_MIN)
case object HOUR extends TimeUnit(TimeConstants.HOUR_LABEL,
  TimeConstants.HOUR_ABBREV, TimeConstants.SEC_PER_HOUR)


sealed abstract class TimeUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, TIME)

