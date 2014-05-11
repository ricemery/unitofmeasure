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
    MILE.label should be (LengthConstants.MileLabel)
    MILE.abbreviation should be (LengthConstants.MileAbbrev)
    MILE.factor should be (LengthConstants.MeterPerMile)
    MILE.standardUnit should be (Meter)
  }

  test("yard") {
    YARD.label should be (LengthConstants.YARD_LABEL)
    YARD.abbreviation should be (LengthConstants.YardAbbrev)
    YARD.factor should be (LengthConstants.MeterPerYard)
    YARD.standardUnit should be (Meter)
  }

  test("foot") {
    FOOT.label should be (LengthConstants.FootLabel)
    FOOT.abbreviation should be (LengthConstants.FootAbbrev)
    FOOT.factor should be (LengthConstants.MeterPerFoot)
    FOOT.standardUnit should be (Meter)
  }

  test("conversionsToStandard") {
    val startMeters: Double = 4.4
    val expectedFromMeters: Double = startMeters
    Meter.convertToStandard(startMeters) should be (expectedFromMeters)

    val startFt: Double = 3.3
    val expectedFromFt: Double = startFt * LengthConstants.MeterPerFoot
    epsilonEquals(FOOT.convertToStandard(startFt), expectedFromFt, epsilon) should be (true)
  }

  test("conversionsFromStandard") {
    val startMeters: Double = 62.8
    val expectedToMeters: Double = startMeters
    Meter.convertFromStandard(startMeters) should be (expectedToMeters)

    val expectedToFt: Double = startMeters / LengthConstants.MeterPerFoot
    epsilonEquals(FOOT.convertFromStandard(startMeters), expectedToFt, epsilon) should be (true)
  }
}
