package com.chainstaysoftware.unitofmeasure

case object Angle extends Category(Radians)
case object Frequency extends Category(Hertz)
case object Length extends Category(Meter)
case object Temperature extends Category(Centigrade)
case object Time extends Category(Second)

sealed abstract class Category(val default: MeasurementUnit)
