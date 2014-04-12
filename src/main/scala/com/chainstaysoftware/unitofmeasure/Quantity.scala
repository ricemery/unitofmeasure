package com.chainstaysoftware.unitofmeasure

case class Quantity(value: Double, measurementUnit: MeasurementUnit, scale: EngineeringScale) {
  def add(other: Quantity): Quantity = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot add quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    val sum = value + matchedOther.value
    new Quantity(sum, measurementUnit, scale)
  }

  def subtract(other: Quantity): Quantity = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot subtract quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    val sum = value - matchedOther.value
    new Quantity(sum, measurementUnit, scale)
  }

  def min(other: Quantity): Quantity = {
    assert(measurementUnit.category.equals(other.measurementUnit.category),
      "Cannot detect min on quantities with different categories")

    val matchedOther = other.convertScaleTo(scale)
    if (matchedOther.value < value)
      other
    else
      this
  }

  def max(other: Quantity): Quantity = {
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
   * objects that are equal in value but have a different {@link EngineeringScale}
   * are considered equal by this method. This method is provided in preference
   * to individual methods for each of the six boolean comparison operators
   * (<, ==, >, >=, !=, <=). The suggested idiom for performing these comparisons is:
   * (x.compareTo(y) <op> 0), where <op> is one of the six comparison operators.
   * @return -1, 0, or 1 as this Quantity is numerically less than, equal to,
   *         or greater than val.
   */
  def compareTo(other: Quantity, epsilon: Double = 0.001): Int = {
    assert(measurementUnit.category == other.measurementUnit.category,
      "Error, cannot compare Quantity with incompatible Units")
    val matchedVal: Quantity = other.convertScaleTo(scale)
    val matchedValue: Double = matchedVal.value
    if (epsilonEquals(matchedValue, epsilon))
      0
    else if (value < matchedValue)
      -1
    else
      1
  }

  def convertUnitsTo(desiredUnit: MeasurementUnit): Quantity = {
    if (desiredUnit.equals(measurementUnit))
      this
    else {
      val desiredValue = desiredUnit.convertFromStandard(measurementUnit.convertToStandard(value))
      new Quantity(desiredValue, desiredUnit, scale)
    }
  }

  def convertScaleTo(desiredScale: EngineeringScale): Quantity = {
    if (desiredScale.equals(scale))
      this
    else {
      val rescaledValue = desiredScale.convertToScaled(scale.convertToUnscaled(value))
      new Quantity(rescaledValue, measurementUnit, desiredScale)
    }
  }

  private def epsilonEquals(y: Double, epsilon: Double) =
  {
    assert(epsilon >= 0.0, "epsilon must be greater than or equal to zero")
    this.value == y || Math.abs(value - y) <= epsilon
  }
}
