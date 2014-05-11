package com.chainstaysoftware.unitofmeasure

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

class QuantityTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  private val epsilon = 1.0E-8

  test("length creation") {
    val kmValue = 12.34
    val kmUnit = METER
    val kmScale = KILO
    val kmQuantity = Quantity[LengthUnit](kmValue, kmUnit, kmScale)

    kmQuantity.value should be (kmValue)
    kmQuantity.measurementUnit should be (METER)
    kmQuantity.scale should be (KILO)
  }

  test("angle creation") {
    val degreeValue = 90.0
    val degreeUnit = DEGREES
    val degreeScale = NONE
    val degreeQuantity = Quantity[AngleUnit](degreeValue, degreeUnit, degreeScale)

    degreeQuantity.value should be (degreeValue)
    degreeQuantity.measurementUnit should be (DEGREES)
    degreeQuantity.scale should be (NONE)
  }

  test("frequency creation") {
    val frequencyValue = 30.0
    val frequencyUnit = HERTZ
    val frequencyScale = MEGA
    val frequencyQuantity = Quantity[FrequencyUnit](frequencyValue, frequencyUnit, frequencyScale)

    frequencyQuantity.value should be (frequencyValue)
    frequencyQuantity.measurementUnit should be (frequencyUnit)
    frequencyQuantity.scale should be (MEGA)
  }

  test("temperature creation") {
    val tempValue = 32.0
    val tempUnit = FAHRENHEIT
    val tempScale = NONE
    val tempQuantity = Quantity[TemperatureUnit](tempValue, tempUnit, tempScale)

    tempQuantity.value should be (tempValue)
    tempQuantity.measurementUnit should be (tempUnit)
    tempQuantity.scale should be (tempScale)
  }

  test("time creation") {
    val timeValue = 1000
    val timeUnit = SECOND
    val timeScale = MILLI
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    timeQuantity.value should be (timeValue)
    timeQuantity.measurementUnit should be (timeUnit)
    timeQuantity.scale should be (timeScale)
  }

  test("length convertUnitsTo") {
    val startMeters = 54.32
    val meterQuantity = Quantity[LengthUnit](startMeters, METER, NONE)

    val expectedFeet = startMeters / LengthConstants.METER_PER_FOOT
    val footQuantity = meterQuantity.convertUnitsTo(FOOT)

    epsilonEquals(footQuantity.value, expectedFeet, epsilon) should be (true)
    footQuantity.measurementUnit should be (FOOT)
    footQuantity.scale should be (NONE)

    val expectedMiles = startMeters / LengthConstants.METER_PER_MILE
    val mileQuantity = meterQuantity.convertUnitsTo(MILE)
    epsilonEquals(mileQuantity.value, expectedMiles, epsilon)
    mileQuantity.measurementUnit should be (MILE)
    mileQuantity.scale should be (NONE)
  }

  test("angle convertUnitsTo") {
    val angleDegrees = 45.0
    val angleDegreesQuantity = Quantity[AngleUnit](angleDegrees, DEGREES, NONE)

    val expectedRadians = angleDegrees / AngleConstants.DEGREE_PER_RADIAN
    val angleRadiansQuantity = angleDegreesQuantity.convertUnitsTo(RADIANS)

    epsilonEquals(angleRadiansQuantity.value, expectedRadians, epsilon) should be (true)
    angleRadiansQuantity.measurementUnit should be (RADIANS)
    angleRadiansQuantity.scale should be (NONE)
  }

  test("temperature convertUnitsTo") {
    val tempValue = 32.0
    val tempUnit = FAHRENHEIT
    val tempScale = NONE
    val tempQuantity = Quantity[TemperatureUnit](tempValue, tempUnit, tempScale)

    val expectedCentigrade = 0
    val temperatureCentigradeQuantity = tempQuantity.convertUnitsTo(CENTIGRADE)

    temperatureCentigradeQuantity should be (Quantity(expectedCentigrade, CENTIGRADE, NONE))

    val boiling = Quantity[TemperatureUnit](100, CENTIGRADE, NONE)
    boiling.convertUnitsTo(FAHRENHEIT) should be (Quantity[TemperatureUnit](212, FAHRENHEIT, NONE))
  }

  test("time convertUnitsTo") {
    val timeValue = 180
    val timeUnit = SECOND
    val timeScale = NONE
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    val timeMinuteQuantity = timeQuantity.convertUnitsTo(MINUTE)

    timeMinuteQuantity should be (Quantity[TimeUnit](3, MINUTE, NONE))
  }

  test("length convertScaleTo") {
    val startMeters = 54.32
    val meterQuantity = Quantity[LengthUnit](startMeters, METER, NONE)

    val expectedMillimeters = startMeters * 1000
    val millimeterQuantity = meterQuantity.convertScaleTo(MILLI)

    millimeterQuantity should be (Quantity[LengthUnit](expectedMillimeters, METER, MILLI))
  }

  test("frequency convertScaleTo") {
    val frequencyValue = 30.0
    val frequencyUnit = HERTZ
    val frequencyScale = MEGA
    val frequencyQuantity = Quantity[FrequencyUnit](frequencyValue, frequencyUnit, frequencyScale)

    val expectedHertz = frequencyValue * MEGA.factor
    val frequencyHertzQuantity = frequencyQuantity.convertScaleTo(NONE)

    frequencyHertzQuantity should be (Quantity(expectedHertz, HERTZ, NONE))
  }

  test("temperature convertScaleTo") {
    val boiling = Quantity[TemperatureUnit](100, CENTIGRADE, NONE)

    boiling.convertScaleTo(MILLI) should be (Quantity[TemperatureUnit](boiling.value / MILLI.factor, CENTIGRADE, MILLI))
  }

  test("time convertScaleTo") {
    val timeValue = 60000
    val timeUnit = SECOND
    val timeScale = MILLI
    val timeQuantity = Quantity[TimeUnit](timeValue, timeUnit, timeScale)

    val secondsQuantity = timeQuantity.convertScaleTo(NONE)

    secondsQuantity should be (Quantity[TimeUnit](60, SECOND, NONE))
  }

  test("length add") {
    val q1 = Quantity[LengthUnit](1, METER, ATTO)
    val q2 = Quantity[LengthUnit](1, METER, ATTO)

    q1.add(q2) should be (Quantity[LengthUnit](2, METER, ATTO))

    val q3 = Quantity[LengthUnit](1, METER, NONE)
    val q4 = Quantity[LengthUnit](1, METER, KILO)

    q4.add(q3) should be (Quantity[LengthUnit](1.001, METER, KILO))
    q3.add(q4) should be (Quantity[LengthUnit](1001, METER, NONE))
  }

  test("length subtract") {
    val q1 = Quantity[LengthUnit](1, METER, ATTO)
    val q2 = Quantity[LengthUnit](1, METER, ATTO)

    q1.subtract(q2) should be (Quantity[LengthUnit](0, METER, ATTO))

    val q3 = Quantity[LengthUnit](1, METER, NONE)
    val q4 = Quantity[LengthUnit](1, METER, KILO)

    q4.subtract(q3) should be (Quantity[LengthUnit](0.999, METER, KILO))
    q3.subtract(q4) should be (Quantity[LengthUnit](-999, METER, NONE))
  }

  test("min") {
    val q1 = Quantity[LengthUnit](1, METER, MILLI)
    val q2 = Quantity[LengthUnit](2, METER, MILLI)
    val q3 = Quantity[LengthUnit](1, METER, KILO)

    q1.min(q1) should be (q1)
    q1.min(q2) should be (q1)
    q2.min(q1) should be (q1)
    q3.min(q2) should be (q2)
  }

  test("max") {
    val q1 = Quantity[LengthUnit](1, METER, MILLI)
    val q2 = Quantity[LengthUnit](2, METER, MILLI)
    val q3 = Quantity[LengthUnit](1, METER, KILO)

    q1.max(q1) should be (q1)
    q1.max(q2) should be (q2)
    q2.max(q1) should be (q2)
    q3.max(q2) should be (q3)
  }

  test("compareTo") {
    val testValue = 123.45
    val q1 = Quantity[LengthUnit](testValue, METER, NONE)
    val defaultEpsilon = 0.001
    val qWithinEpsilonA = q1.add(Quantity[LengthUnit](defaultEpsilon / 2, METER, NONE))
    val qWithinEpsilonB = q1.subtract(Quantity[LengthUnit](defaultEpsilon / 2, METER, NONE))
    val qAboveEpsilon = q1.add(Quantity[LengthUnit](1, METER, NONE))
    val qBelowEpsilon = q1.subtract(Quantity[LengthUnit](1, METER, NONE))

    q1.compareTo(q1) should be (0)
    qWithinEpsilonA.compareTo(q1) should be (0)
    qWithinEpsilonB.compareTo(q1) should be (0)
    qAboveEpsilon.compareTo(q1) should be (1)
    qBelowEpsilon.compareTo(q1) should be (-1)
  }
}
