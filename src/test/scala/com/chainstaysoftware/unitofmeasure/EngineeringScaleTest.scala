package com.chainstaysoftware.unitofmeasure

import org.scalatest.{Matchers, FunSuite, BeforeAndAfter}

class EngineeringScaleTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  test("test factor") {
    KILO.factor should be (1000.0)
    ATTO.factor should be (1E-18)
  }

  test("test prefix") {
    KILO.prefix should be ("kilo")
    ATTO.prefix should be ("atto")
  }

  test("test symbol") {
    KILO.symbol should be ("K")
    ATTO.symbol should be ("a")
  }

  test("test conversions") {
    val epsilon: Double = 1.0E-10
    val scaledKilo: Double = 12.34
    val unscaledKilo: Double = scaledKilo * 1000.0

    epsilonEquals(KILO.convertToUnscaled(scaledKilo), unscaledKilo, epsilon) should be (true)
    epsilonEquals(KILO.convertToScaled(unscaledKilo), scaledKilo, epsilon) should be (true)
  }

  test("test scale") {
    val smallest: Double = 1.0E-20
    val smaller: Double = 1.0E-14
    val small: Double = 1.0E-4
    val justRight: Double = 1.0
    val big: Double = 1.0E4
    val bigger: Double = 1.0E14
    val biggest: Double = 1.0E20

    val smallestScale = ATTO
    val smallerScale = FEMTO
    val smallScale = MICRO
    val justRightScale = NONE
    val bigScale = KILO
    val biggerScale = TERA
    val biggestScale = EXA

    NONE.getScale(smallest) should be (smallestScale)
    NONE.getScale(smaller) should be (smallerScale)
    NONE.getScale(small) should be (smallScale)
    NONE.getScale(justRight) should be (justRightScale)
    NONE.getScale(big) should be (bigScale)
    NONE.getScale(bigger) should be (biggerScale)
    NONE.getScale(biggest) should be (biggestScale)
  }
}
