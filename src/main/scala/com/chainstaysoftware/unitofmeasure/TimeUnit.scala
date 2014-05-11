package com.chainstaysoftware.unitofmeasure

case object Second extends TimeUnit(TimeConstants.SecondLabel,
  TimeConstants.SecondAbbrev, 1.0)
case object Minute extends TimeUnit(TimeConstants.MinuteLabel,
  TimeConstants.MinuteAbbrev, TimeConstants.SecPerMin)
case object Hour extends TimeUnit(TimeConstants.HourLabel,
  TimeConstants.HourAbbrev, TimeConstants.SecPerHour)


sealed abstract class TimeUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Time)

