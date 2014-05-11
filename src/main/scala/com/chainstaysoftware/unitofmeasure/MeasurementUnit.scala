package com.chainstaysoftware.unitofmeasure

abstract class MeasurementUnit(val label: String, val abbreviation: String,
                               val factor: Double, val category: Category) {
  def standardUnit: LengthUnit = Meter

  def convertToStandard(value: Double) = value * factor

  def convertFromStandard(value : Double) = value / factor
}
