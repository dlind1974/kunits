package com.github.dlind1974.kunits

class UnitException(message: String) : Exception(message)

interface UnitConverter {
    fun toBase(amount: Double) : Double
    fun fromBase(amount: Double) : Double
}

class RatioUnitConverter(val ratio: Double) : UnitConverter {
    override fun toBase(amount: Double): Double {
        return amount * ratio
    }

    override fun fromBase(amount: Double): Double {
        return amount / ratio
    }
}

typealias AmountConversion = (amount: Double)-> Double

class FormulaUnitConverter(val convertToBase: AmountConversion, val convertFromBase: AmountConversion) : UnitConverter{
    override fun toBase(amount: Double): Double {
        return convertToBase(amount)
    }

    override fun fromBase(amount: Double): Double {
        return convertFromBase(amount)
    }
}

open class MeasureUnit(val name: String, private val unitConverter: UnitConverter) {
    fun convertToBaseUnit(amount: Double) = unitConverter.toBase(amount) // amount * ratio
    fun convertFromBaseUnit(amount: Double) = unitConverter.fromBase(amount)  // amount / ratio
}