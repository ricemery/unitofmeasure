package com.chainstaysoftware.unitofmeasure

import org.scalatest.{Matchers, FunSuite, BeforeAndAfter}

class EngineeringScaleTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  test("test factor") {
    Kilo.factor should be (1000.0)
    Atto.factor should be (1E-18)
  }

  test("test prefix") {
    Kilo.prefix should be ("kilo")
    Atto.prefix should be ("atto")
  }

  test("test symbol") {
    Kilo.symbol should be ("K")
    Atto.symbol should be ("a")
  }

  test("test conversions") {
    val epsilon: Double = 1.0E-10
    val scaledKilo: Double = 12.34
    val unscaledKilo: Double = scaledKilo * 1000.0

    epsilonEquals(Kilo.convertToUnscaled(scaledKilo), unscaledKilo, epsilon) should be (true)
    epsilonEquals(Kilo.convertToScaled(unscaledKilo), scaledKilo, epsilon) should be (true)
  }

  test("test scale") {
    val smallest: Double = 1.0E-20
    val smaller: Double = 1.0E-14
    val small: Double = 1.0E-4
    val justRight: Double = 1.0
    val big: Double = 1.0E4
    val bigger: Double = 1.0E14
    val biggest: Double = 1.0E20

    val smallestScale = Atto
    val smallerScale = Femto
    val smallScale = Micro
    val justRightScale = None
    val bigScale = Kilo
    val biggerScale = Tera
    val biggestScale = Exa

    None.getScale(smallest) should be (smallestScale)
    None.getScale(smaller) should be (smallerScale)
    None.getScale(small) should be (smallScale)
    None.getScale(justRight) should be (justRightScale)
    None.getScale(big) should be (bigScale)
    None.getScale(bigger) should be (biggerScale)
    None.getScale(biggest) should be (biggestScale)
  }
}
