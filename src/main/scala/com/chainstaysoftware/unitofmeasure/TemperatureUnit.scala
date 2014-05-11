package com.chainstaysoftware.unitofmeasure

case object Centigrade extends TemperatureUnit(TemperatureConstants.CentigradeLabel,
  TemperatureConstants.CentigradeAbbrev, 1.0, 0.0)
case object FAHRENHEIT extends TemperatureUnit(TemperatureConstants.FahrenheitLabel,
  TemperatureConstants.FahrenheitAbbrev, TemperatureConstants.CentigradePerFahrenheitScale,
  TemperatureConstants.CentigradeToFahrenheitOffset)

sealed abstract class TemperatureUnit(label: String, abbreviation: String, factor: Double, offset: Double)
  extends MeasurementUnit(label, abbreviation, factor, Temperature) {

  override def convertFromStandard(value: Double) = (value / factor) + offset

  override def convertToStandard(value: Double) = (value - offset) * factor
}
