package math

import kotlin.math.abs

class RationalNumber(_numerator: Int, _denominator: Int) {
    private var numerator = 0
    private var denominator = 0

    constructor(_numerator: Int) : this(_numerator, 1)

    init {
        if (_denominator == 0) throw IllegalArgumentException()
        if (_denominator < 0) {
            numerator = _numerator * -1
            denominator = _denominator * -1
        } else {
            numerator = _numerator
            denominator = _denominator
        }
        reduce()
    }

    fun reciprocal() : RationalNumber = RationalNumber(denominator, numerator)
    fun negative() : RationalNumber = RationalNumber(-numerator, denominator)

    fun add(nr: RationalNumber) : RationalNumber {
        val commonDenominator = denominator * nr.denominator
        val numerator1 = numerator * nr.denominator
        val numerator2 = nr.numerator * denominator
        val sum = numerator1 + numerator2

        return RationalNumber(sum, commonDenominator)
    }

    fun subtract(nr: RationalNumber) : RationalNumber {
        val commonDenominator: Int = denominator * nr.denominator
        val numerator1: Int = numerator * nr.denominator
        val numerator2: Int = nr.numerator * denominator
        val difference = numerator1 - numerator2

        return RationalNumber(difference, commonDenominator)
    }

    fun multiply(nr: RationalNumber) : RationalNumber {
        val newNumerator = numerator * nr.numerator
        val newDenominator = denominator * nr.denominator

        return RationalNumber(newNumerator, newDenominator)
    }

    fun divide(nr: RationalNumber) : RationalNumber {
        return multiply(nr.reciprocal())
    }

    fun getDenominator() = denominator
    fun getNumerator() = numerator

    override fun equals(other: Any?): Boolean {
        return (other is RationalNumber) && (denominator == other.denominator && numerator == other.numerator)
    }

    override fun toString(): String {
        if (numerator == 0) return "0"
        if (denominator == 1) return "$numerator"
        return "$numerator/$denominator"
    }

    override fun hashCode(): Int {
        return "${numerator.hashCode()}${denominator.hashCode()}".toInt()
    }

    private fun reduce() {
        if (numerator != 0) {
            val common: Int = findGreatestCommonDivisor(abs(numerator), denominator)
            numerator /= common
            denominator /= common
        }
    }

    private fun findGreatestCommonDivisor(_x: Int, _y: Int) : Int {
        var x = _x
        var y = _y
        while (x != y) {
            if (x > y) x -= y
            else y -= x
        }
        return x
    }

}