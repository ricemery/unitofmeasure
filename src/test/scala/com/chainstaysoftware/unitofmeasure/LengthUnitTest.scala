package com.chainstaysoftware.unitofmeasure

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

class LengthUnitTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  private val epsilon = 1.0E-14

  test("meter") {
    Meter.label should be (LengthConstants.MeterLabel)
    Meter.abbreviation should be (LengthConstants.MeterAbbrev)
    Meter.factor should be (1.0)
    Meter.standardUnit should be (Meter)
  }

  test("mile") {
    Mile.label should be (LengthConstants.MileLabel)
    Mile.abbreviation should be (LengthConstants.MileAbbrev)
    Mile.factor should be (LengthConstants.MeterPerMile)
    Mile.standardUnit should be (Meter)
  }

  test("yard") {
    Yard.label should be (LengthConstants.YARD_LABEL)
    Yard.abbreviation should be (LengthConstants.YardAbbrev)
    Yard.factor should be (LengthConstants.MeterPerYard)
    Yard.standardUnit should be (Meter)
  }

  test("foot") {
    Foot.label should be (LengthConstants.FootLabel)
    Foot.abbreviation should be (LengthConstants.FootAbbrev)
    Foot.factor should be (LengthConstants.MeterPerFoot)
    Foot.standardUnit should be (Meter)
  }

  test("conversionsToStandard") {
    val startMeters: Double = 4.4
    val expectedFromMeters: Double = startMeters
    Meter.convertToStandard(startMeters) should be (expectedFromMeters)

    val startFt: Double = 3.3
    val expectedFromFt: Double = startFt * LengthConstants.MeterPerFoot
    epsilonEquals(Foot.convertToStandard(startFt), expectedFromFt, epsilon) should be (true)
  }

  test("conversionsFromStandard") {
    val startMeters: Double = 62.8
    val expectedToMeters: Double = startMeters
    Meter.convertFromStandard(startMeters) should be (expectedToMeters)

    val expectedToFt: Double = startMeters / LengthConstants.MeterPerFoot
    epsilonEquals(Foot.convertFromStandard(startMeters), expectedToFt, epsilon) should be (true)
  }
}
