/*
 * Project and Training 1 - Computer Science, Berner Fachhochschule
 */

@file:JvmName("RationalCalculator")
package math

import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

fun convert(input : String) : RationalNumber {
    val valArray = input.split("/")
    if (valArray.isEmpty()) throw IllegalArgumentException()
    return RationalNumber(valArray[0].toInt(), if (valArray.size > 1) valArray[1].toInt() else 1)
}

fun evaluate(input : String) : RationalNumber? {
    var result : RationalNumber? = null
    val operations = ("[+\\-*:]").toRegex().findAll(input)
    val newNumbers = ("-?[0-9]+(/-?[0-9]+)?").toRegex().findAll(input)
    val numbersIterator = newNumbers.iterator()
    for (op in operations) {
        val num1 = numbersIterator.next().value.split("/")
        result = if (result == null) executeEvaluationPart(op.value, RationalNumber(num1[0].toInt(), if (num1.size > 1) num1[1].toInt() else 1), numbersIterator.next().value.split("/"))
                else executeEvaluationPart(op.value, result, num1)
        }
    return result
}

fun main() {
    var s = Scanner(System.`in`)
    print("write some rational expression please")
}

private fun executeEvaluationPart(operator: String, numberOne: RationalNumber, numberTwo: List<String>) : RationalNumber {
    when (operator) {
        "+" -> return numberOne.add(RationalNumber(numberTwo[0].toInt(), if (numberTwo.size > 1) numberTwo[1].toInt() else 1))
        "-" -> return numberOne.subtract(RationalNumber(numberTwo[0].toInt(), if (numberTwo.size > 1) numberTwo[1].toInt() else 1))
        "*" -> return numberOne.multiply(RationalNumber(numberTwo[0].toInt(), if (numberTwo.size > 1) numberTwo[1].toInt() else 1))
        ":" -> return numberOne.divide(RationalNumber(numberTwo[0].toInt(), if (numberTwo.size > 1) numberTwo[1].toInt() else 1))
        else -> throw IOException()
    }
}

