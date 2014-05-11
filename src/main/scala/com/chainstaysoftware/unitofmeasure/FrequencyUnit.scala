package com.chainstaysoftware.unitofmeasure

case object Hertz extends FrequencyUnit(FrequencyConstants.HetrzLabel,
  FrequencyConstants.HertzAbbrev, 1.0)

sealed abstract class FrequencyUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, Frequency)
