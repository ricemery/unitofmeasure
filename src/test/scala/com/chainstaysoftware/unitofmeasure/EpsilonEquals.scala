package com.chainstaysoftware.unitofmeasure

trait EpsilonEquals {
  def epsilonEquals(val1: Double, val2: Double, epsilon: Double) = {
    val1.compare(val2) == 0 || Math.abs(val1 - val2) < epsilon
  }
}
