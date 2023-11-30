package com.github.dlind1974.kunits

import kotlinx.serialization.Serializable

class UnitException(message: String) : Exception(message)

@Serializable
abstract class MeasureUnit {
    abstract val name: String
    abstract val ratio: Double
    fun convertToBaseUnit(amount: Double) = amount * ratio
    fun convertFromBaseUnit(amount: Double) = amount / ratio
}

