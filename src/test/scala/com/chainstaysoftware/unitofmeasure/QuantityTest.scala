package com.chainstaysoftware.unitofmeasure

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

class QuantityTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  private val epsilon = 1.0E-8

  test("length creation") {
    val kmValue = 12.34
    val kmUnit = Meter
    val kmScale = Kilo
    val kmQuantity = Quantity[LengthUnit](kmValue, kmUnit, kmScale)

    kmQuantity.value should be (kmValue)
    kmQuantity.measurementUnit should be (Meter)
    kmQuantity.scale should be (Kilo)
  }

  test("angle creation") {
    val degreeValue = 90.0
    val degreeUnit = Degrees
    val degreeScale = None
    val degreeQuantity = Quantity[AngleUnit](degreeValue, degreeUnit, degreeScale)

    degreeQuantity.value should be (degreeValue)
    degreeQuantity.measurementUnit should be (Degrees)
    degreeQuantity.scale should be (None)
  }

  test("frequency creation") {
    val frequencyValue = 30.0
    val frequencyUnit = Hertz
    val frequencyScale = Mega
    val frequencyQuantity = Quantity[FrequencyUnit](frequencyValue, frequencyUnit, frequencyScale)

    frequencyQuantity.value should be (frequencyValue)
    frequencyQuantity.measurementUnit should be (frequencyUnit)
    frequencyQuantity.scale should be (Mega)
  }

  test("temperature creation") {
    val tempValue = 32.0
    val tempUnit = FAHRENHEIT
    val tempScale = None
    val tempQuantity = Quantity[TemperatureUnit](tempValue, tempUnit, tempScale)

    tempQuantity.value should be (tempValue)
    tempQuantity.measurementUnit should be (tempUnit)
    tempQuantity.scale should be (tempScale)
  }

  test("time creation") {
    val timeValue = 1000
    val timeUnit = Second
    val timeScale = Milli
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    timeQuantity.value should be (timeValue)
    timeQuantity.measurementUnit should be (timeUnit)
    timeQuantity.scale should be (timeScale)
  }

  test("length convertUnitsTo") {
    val startMeters = 54.32
    val meterQuantity = Quantity[LengthUnit](startMeters, Meter, None)

    val expectedFeet = startMeters / LengthConstants.MeterPerFoot
    val footQuantity = meterQuantity.convertUnitsTo(Foot)

    epsilonEquals(footQuantity.value, expectedFeet, epsilon) should be (true)
    footQuantity.measurementUnit should be (Foot)
    footQuantity.scale should be (None)

    val expectedMiles = startMeters / LengthConstants.MeterPerMile
    val mileQuantity = meterQuantity.convertUnitsTo(Mile)
    epsilonEquals(mileQuantity.value, expectedMiles, epsilon)
    mileQuantity.measurementUnit should be (Mile)
    mileQuantity.scale should be (None)
  }

  test("angle convertUnitsTo") {
    val angleDegrees = 45.0
    val angleDegreesQuantity = Quantity[AngleUnit](angleDegrees, Degrees, None)

    val expectedRadians = angleDegrees / AngleConstants.DegreePerRadian
    val angleRadiansQuantity = angleDegreesQuantity.convertUnitsTo(Radians)

    epsilonEquals(angleRadiansQuantity.value, expectedRadians, epsilon) should be (true)
    angleRadiansQuantity.measurementUnit should be (Radians)
    angleRadiansQuantity.scale should be (None)
  }

  test("temperature convertUnitsTo") {
    val tempValue = 32.0
    val tempUnit = FAHRENHEIT
    val tempScale = None
    val tempQuantity = Quantity[TemperatureUnit](tempValue, tempUnit, tempScale)

    val expectedCentigrade = 0
    val temperatureCentigradeQuantity = tempQuantity.convertUnitsTo(Centigrade)

    temperatureCentigradeQuantity should be (Quantity(expectedCentigrade, Centigrade, None))

    val boiling = Quantity[TemperatureUnit](100, Centigrade, None)
    boiling.convertUnitsTo(FAHRENHEIT) should be (Quantity[TemperatureUnit](212, FAHRENHEIT, None))
  }

  test("time convertUnitsTo") {
    val timeValue = 180
    val timeUnit = Second
    val timeScale = None
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    val timeMinuteQuantity = timeQuantity.convertUnitsTo(Minute)

    timeMinuteQuantity should be (Quantity[TimeUnit](3, Minute, None))
  }

  test("length convertScaleTo") {
    val startMeters = 54.32
    val meterQuantity = Quantity[LengthUnit](startMeters, Meter, None)

    val expectedMillimeters = startMeters * 1000
    val millimeterQuantity = meterQuantity.convertScaleTo(Milli)

    millimeterQuantity should be (Quantity[LengthUnit](expectedMillimeters, Meter, Milli))
  }

  test("frequency convertScaleTo") {
    val frequencyValue = 30.0
    val frequencyUnit = Hertz
    val frequencyScale = Mega
    val frequencyQuantity = Quantity[FrequencyUnit](frequencyValue, frequencyUnit, frequencyScale)

    val expectedHertz = frequencyValue * Mega.factor
    val frequencyHertzQuantity = frequencyQuantity.convertScaleTo(None)

    frequencyHertzQuantity should be (Quantity(expectedHertz, Hertz, None))
  }

  test("temperature convertScaleTo") {
    val boiling = Quantity[TemperatureUnit](100, Centigrade, None)

    boiling.convertScaleTo(Milli) should be (Quantity[TemperatureUnit](boiling.value / Milli.factor, Centigrade, Milli))
  }

  test("time convertScaleTo") {
    val timeValue = 60000
    val timeUnit = Second
    val timeScale = Milli
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    val secondsQuantity = timeQuantity.convertScaleTo(None)

    secondsQuantity should be (Quantity[TimeUnit](60, Second, None))
  }

  test("length add") {
    val q1 = Quantity[LengthUnit](1, Meter, Atto)
    val q2 = Quantity[LengthUnit](1, Meter, Atto)

    q1.add(q2) should be (Quantity[LengthUnit](2, Meter, Atto))

    val q3 = Quantity[LengthUnit](1, Meter, None)
    val q4 = Quantity[LengthUnit](1, Meter, Kilo)

    q4.add(q3) should be (Quantity[LengthUnit](1.001, Meter, Kilo))
    q3.add(q4) should be (Quantity[LengthUnit](1001, Meter, None))
  }

  test("length subtract") {
    val q1 = Quantity[LengthUnit](1, Meter, Atto)
    val q2 = Quantity[LengthUnit](1, Meter, Atto)

    q1.subtract(q2) should be (Quantity[LengthUnit](0, Meter, Atto))

    val q3 = Quantity[LengthUnit](1, Meter, None)
    val q4 = Quantity[LengthUnit](1, Meter, Kilo)

    q4.subtract(q3) should be (Quantity[LengthUnit](0.999, Meter, Kilo))
    q3.subtract(q4) should be (Quantity[LengthUnit](-999, Meter, None))
  }

  test("min") {
    val q1 = Quantity[LengthUnit](1, Meter, Milli)
    val q2 = Quantity[LengthUnit](2, Meter, Milli)
    val q3 = Quantity[LengthUnit](1, Meter, Kilo)

    q1.min(q1) should be (q1)
    q1.min(q2) should be (q1)
    q2.min(q1) should be (q1)
    q3.min(q2) should be (q2)
  }

  test("max") {
    val q1 = Quantity[LengthUnit](1, Meter, Milli)
    val q2 = Quantity[LengthUnit](2, Meter, Milli)
    val q3 = Quantity[LengthUnit](1, Meter, Kilo)

    q1.max(q1) should be (q1)
    q1.max(q2) should be (q2)
    q2.max(q1) should be (q2)
    q3.max(q2) should be (q3)
  }

  test("compareTo") {
    val testValue = 123.45
    val q1 = Quantity[LengthUnit](testValue, Meter, None)
    val defaultEpsilon = 0.001
    val qWithinEpsilonA = q1.add(Quantity[LengthUnit](defaultEpsilon / 2, Meter, None))
    val qWithinEpsilonB = q1.subtract(Quantity[LengthUnit](defaultEpsilon / 2, Meter, None))
    val qAboveEpsilon = q1.add(Quantity[LengthUnit](1, Meter, None))
    val qBelowEpsilon = q1.subtract(Quantity[LengthUnit](1, Meter, None))

    q1.compareTo(q1) should be (0)
    qWithinEpsilonA.compareTo(q1) should be (0)
    qWithinEpsilonB.compareTo(q1) should be (0)
    qAboveEpsilon.compareTo(q1) should be (1)
    qBelowEpsilon.compareTo(q1) should be (-1)
  }

  test("compareToAltUnits") {
    val testValue = 1
    val q1 = Quantity[LengthUnit](testValue, Meter, Kilo)
    val q2 = q1.convertUnitsTo(Foot)

    q1.compareTo(q2) should be (0)
  }
}
