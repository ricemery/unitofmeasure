package com.chainstaysoftware.unitofmeasure

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

class LengthUnitTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  private val epsilon = 1.0E-14

  test("meter") {
    METER.label should be (LengthConstants.METER_LABEL)
    METER.abbreviation should be (LengthConstants.METER_ABBREV)
    METER.factor should be (1.0)
    METER.standardUnit should be (METER)
  }

  test("mile") {
    MILE.label should be (LengthConstants.MILE_LABEL)
    MILE.abbreviation should be (LengthConstants.MILE_ABBREV)
    MILE.factor should be (LengthConstants.METER_PER_MILE)
    MILE.standardUnit should be (METER)
  }

  test("yard") {
    YARD.label should be (LengthConstants.YARD_LABEL)
    YARD.abbreviation should be (LengthConstants.YARD_ABBREV)
    YARD.factor should be (LengthConstants.METER_PER_YARD)
    YARD.standardUnit should be (METER)
  }

  test("foot") {
    FOOT.label should be (LengthConstants.FOOT_LABEL)
    FOOT.abbreviation should be (LengthConstants.FOOT_ABBREV)
    FOOT.factor should be (LengthConstants.METER_PER_FOOT)
    FOOT.standardUnit should be (METER)
  }

  test("conversionsToStandard") {
    val startMeters: Double = 4.4
    val expectedFromMeters: Double = startMeters
    METER.convertToStandard(startMeters) should be (expectedFromMeters)

    val startFt: Double = 3.3
    val expectedFromFt: Double = startFt * LengthConstants.METER_PER_FOOT
    epsilonEquals(FOOT.convertToStandard(startFt), expectedFromFt, epsilon) should be (true)
  }

  test("conversionsFromStandard") {
    val startMeters: Double = 62.8
    val expectedToMeters: Double = startMeters
    METER.convertFromStandard(startMeters) should be (expectedToMeters)

    val expectedToFt: Double = startMeters / LengthConstants.METER_PER_FOOT
    epsilonEquals(FOOT.convertFromStandard(startMeters), expectedToFt, epsilon) should be (true)
  }
}
