package com.chainstaysoftware.unitofmeasure

import EqualsHelper.DoubleWithEpsilonEquals

case class Quantity[T <: MeasurementUnit](value: Double, measurementUnit: T, scale: EngineeringScale) {
  def add(other: Quantity[T]): Quantity[T] = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot add quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    val sum = value + matchedOther.value
    Quantity(sum, measurementUnit, scale)
  }

  def subtract(other: Quantity[T]): Quantity[T] = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot subtract quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    val sum = value - matchedOther.value
    Quantity(sum, measurementUnit, scale)
  }

  def min(other: Quantity[T]): Quantity[T] = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot detect min on quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    if (matchedOther.value < value)
      other
    else
      this
  }

  def max(other: Quantity[T]): Quantity[T] = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot detect min on quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    if (matchedOther.value > value)
      other
    else
      this
  }

  /**
   * Compares this {@link Quantity} with the specified {@link Quantity}. Two {@link Quantity}
   * objects that are equal when converted to a common {@link EngineeringScale} and {@link MeasurementUnit}
   * are considered equal by this method. This method is provided in preference
   * to individual methods for each of the six boolean comparison operators
   * (<, ==, >, >=, !=, <=). The suggested idiom for performing these comparisons is:
   * (x.compareTo(y) <op> 0), where <op> is one of the six comparison operators.
   * @return -1, 0, or 1 as this Quantity is numerically less than, equal to,
   *         or greater than val.
   */
  def compareTo(other: Quantity[T], epsilon: Double = 0.001): Int = {
    assert(measurementUnit.category == other.measurementUnit.category,
      "Error, cannot compare Quantity with incompatible Units")
    val matchedVal = other.convertUnitsTo(measurementUnit).convertScaleTo(scale)
    val matchedValue = matchedVal.value
    implicit val precision = Precision(epsilon)
    if (value ~= matchedValue)
      0
    else if (value < matchedValue)
      -1
    else
      1
  }

  def convertUnitsTo(desiredUnit: T): Quantity[T] = {
    if (desiredUnit.equals(measurementUnit))
      this
    else {
      val desiredValue = desiredUnit.convertFromStandard(measurementUnit.convertToStandard(value))
      new Quantity(desiredValue, desiredUnit, scale)
    }
  }

  def convertScaleTo(desiredScale: EngineeringScale): Quantity[T] = {
    if (desiredScale.equals(scale))
      this
    else {
      val rescaledValue = desiredScale.convertToScaled(scale.convertToUnscaled(value))
      new Quantity(rescaledValue, measurementUnit, desiredScale)
    }
  }
}
