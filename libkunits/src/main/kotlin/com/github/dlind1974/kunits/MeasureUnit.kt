package com.github.dlind1974.kunits

class UnitException(message: String) : Exception(message)

open class MeasureUnit(val name: String, val ratio: Double) {
    fun convertToBaseUnit(amount: Double) = amount * ratio
    fun convertFromBaseUnit(amount: Double) = amount / ratio
}