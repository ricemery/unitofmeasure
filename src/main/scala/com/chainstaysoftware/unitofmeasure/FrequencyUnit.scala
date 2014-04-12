package com.chainstaysoftware.unitofmeasure

case object HERTZ extends FrequencyUnit(FrequencyConstants.HERTZ_LABEL,
  FrequencyConstants.HERTZ_ABBREV, 1.0)

sealed abstract class FrequencyUnit(label: String, abbreviation: String, factor: Double)
  extends MeasurementUnit(label, abbreviation, factor, FREQUENCY)
