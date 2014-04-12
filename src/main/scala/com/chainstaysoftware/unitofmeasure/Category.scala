package com.chainstaysoftware.unitofmeasure

case object ANGLE extends Category(RADIANS)
case object FREQUENCY extends Category(HERTZ)
case object LENGTH extends Category(METER)
case object TEMPERATURE extends Category(CENTIGRADE)
case object TIME extends Category(SECOND)

sealed abstract class Category(val default: MeasurementUnit)
