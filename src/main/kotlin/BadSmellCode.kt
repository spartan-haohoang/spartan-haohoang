package com.test

val unusedVariable = 42

fun doSomethingVeryComplicatedAndUnnecessarilyLongNamed(x: Int, y: Int): Int {
  val result = x + y * 42
  return result
}

fun CalculateSum(a: Int, b: Int): Int {
  return a + b
}

fun checkNumber(num: Int): String {
  if (num > 0) {
    return "Positive"
  } else if (num < 0) {
    return "Negative"
  } else {
    return "Zero"
  }
}

fun printLength(text: String?) {
  println(text!!.length)
}

fun processData(data: List<Int?>) {
  for (item in data) {
    if (item != null) {
      if (item > 10) {
        if (item % 2 == 0) {
          println("Even and greater than 10: $item")
        }
      }
    }
  }
}

fun unusedFunction() {
  println("This function is never called")
}

fun main() {
  println("Hello, World!")
  val sum = CalculateSum(5, 10)
  println("Sum: $sum")

  doSomethingVeryComplicatedAndUnnecessarilyLongNamed(1, 2)

  printLength("Dummy text")

  val numbers = listOf(1, 2, 3, 4, 5, null, 6, 7, 8, 9, 10)
  processData(numbers)
}