package com.chainstaysoftware.unitofmeasure

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}


class SpeedQuantityTest extends FunSuite with Matchers with BeforeAndAfter with EpsilonEquals {
  test("convertTimeTo") {
    val s1 = SpeedQuantity(Quantity[LengthUnit](100, Mile, None), Hour, None)
    val s2 = SpeedQuantity(Quantity[LengthUnit](1, Meter, Kilo), Hour, None)

    s1.convertTimeTo(Second, None) should be (SpeedQuantity(Quantity[LengthUnit](0.027777777777777776, Mile, None), Second, None))
    s2.convertTimeTo(Second, None) should be (SpeedQuantity(Quantity[LengthUnit](0.00027777777777777776, Meter, Kilo), Second, None))

    val s3 = SpeedQuantity(Quantity[LengthUnit](1, Meter, Milli), Second, None)
    val s4 = SpeedQuantity(Quantity[LengthUnit](0.001, Meter, Milli), Second, Milli)
    s3.convertTimeTo(Second, Milli) should be (s4)
    s4.convertTimeTo(Second, None) should be (s3)
  }

  test("convertScaleTo") {
    val s1 = SpeedQuantity(Quantity[LengthUnit](1, Meter, Kilo), Hour, None)

    s1.convertScaleTo(None) should be (SpeedQuantity(Quantity[LengthUnit](1000, Meter, None), Hour, None))
  }

  test("converUnitsTo") {
    val s1 = SpeedQuantity(Quantity[LengthUnit](60, Meter, Kilo), Hour, None)

    s1.convertUnitsTo(Mile).convertScaleTo(None) should be (SpeedQuantity(Quantity[LengthUnit](37.28236419898841, Mile, None), Hour, None))
  }

  test("compareTo") {
    val s1 = SpeedQuantity(Quantity[LengthUnit](60, Meter, Kilo), Hour, None)
    val s2 = s1.convertUnitsTo(Mile).convertScaleTo(None)
    val s3 = SpeedQuantity(Quantity[LengthUnit](61, Meter, Kilo), Hour, None)

    s1.compareTo(s2) should be (0)
    s1.compareTo(s3) should be (-1)
    s3.compareTo(s1) should be (1)
  }
}
