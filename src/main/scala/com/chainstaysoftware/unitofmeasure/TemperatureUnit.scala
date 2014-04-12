package com.chainstaysoftware.unitofmeasure

case object CENTIGRADE extends TemperatureUnit(TemperatureConstants.CENTIGRADE_LABEL,
  TemperatureConstants.CENTIGRADE_ABBREV, 1.0, 0.0)
case object FAHRENHEIT extends TemperatureUnit(TemperatureConstants.FAHRENHEIT_LABEL,
  TemperatureConstants.FAHRENHEIT_ABBREV, TemperatureConstants.CENTIGRADE_PER_FAHRENHEIT_SCALE,
  TemperatureConstants.CENTIGRADE_TO_FAHRENHEIT_OFFSET)

sealed abstract class TemperatureUnit(label: String, abbreviation: String, factor: Double, offset: Double)
  extends MeasurementUnit(label, abbreviation, factor, TEMPERATURE) {

  override def convertFromStandard(value: Double) = (value / factor) + offset

  override def convertToStandard(value: Double) = (value - offset) * factor
}
