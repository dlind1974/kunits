package com.github.dlind1974.kunits

class UnitException(message: String) : Exception(message)

abstract class MeasureUnit {
    abstract val name: String
    abstract val ratio: Double
    fun convertToBaseUnit(amount: Double) = amount * ratio
    fun convertFromBaseUnit(amount: Double) = amount / ratio
}

