package com.chainstaysoftware.unitofmeasure

import EqualsHelper.DoubleWithEpsilonEquals

/**
 * Holds a speed value like 100kph.
 * example - 100kph would be SpeedQuantity(Quantity[LengthUnit](100,
 * LengthUnit.Meter, EngineeringScale.KILO), TimeUnit.Hour, EngineeringScale.NONE))
 */
case class SpeedQuantity(length: Quantity[LengthUnit], timeUnit: TimeUnit, timeScale: EngineeringScale) {

  /**
   * Compares this {@link SpeedQuantity} with the specified {@link SpeedQuantity}.
   * Two {@link SpeedQuantity} objects that are equal in when converted to a common
   * {@link EngineeringScale} and {@link MeasurementUnit} are considered equal by this method.
   * This method is provided in preference to individual methods for each of the
   * six boolean comparison operators (<, ==, >, >=, !=, <=). The suggested idiom
   * for performing these comparisons is:
   * (x.compareTo(y) <op> 0), where <op> is one of the six comparison operators.
   * @return -1, 0, or 1 as this Quantity is numerically less than, equal to,
   *         or greater than val.
   */
  def compareTo(other: SpeedQuantity, epsilon: Double = 0.001): Int = {
    val otherMatchedTime = other.convertTimeTo(timeUnit, timeScale)
    val otherMatched = otherMatchedTime.convertUnitsTo(length.measurementUnit).convertScaleTo(length.scale)
    val otherMatchedValue = otherMatched.length.value
    implicit val precision = Precision(epsilon)
    if (length.value ~= otherMatchedValue)
      0
    else if (length.value < otherMatchedValue)
      -1
    else
      1
  }

  def convertTimeTo(desiredTimeUnit: TimeUnit, desiredTimeScale: EngineeringScale) = {
    val divisor = Quantity[TimeUnit](1, timeUnit, timeScale).convertScaleTo(desiredTimeScale).convertUnitsTo(desiredTimeUnit)
    SpeedQuantity(Quantity[LengthUnit](length.value / divisor.value,
      length.measurementUnit, length.scale),
      desiredTimeUnit, desiredTimeScale)
  }

  def convertScaleTo(desiredScale: EngineeringScale) =
    SpeedQuantity(length.convertScaleTo(desiredScale),
      timeUnit, timeScale)

  def convertUnitsTo(desiredUnit: LengthUnit) =
    SpeedQuantity(length.convertUnitsTo(desiredUnit), timeUnit, timeScale)

  private def epsilonEquals(value: Double, y: Double, epsilon: Double) =
  {
    assert(epsilon >= 0.0, "epsilon must be greater than or equal to zero")
    value == y || Math.abs(value - y) <= epsilon
  }
}
