package com.chainstaysoftware.unitofmeasure

case object Second extends TimeUnit(TimeConstants.SecondLabel,
  TimeConstants.SecondAbbrev, 1.0)
case object MINUTE extends TimeUnit(TimeConstants.MinuteLabel,
  TimeConstants.MinuteAbbrev, TimeConstants.SecPerMin)
case object HOUR extends TimeUnit(TimeConstants.HourLabel,
  TimeConstants.HourAbbrev, TimeConstants.SecPerHour)


sealed abstract class TimeUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Time)

