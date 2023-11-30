package com.github.dlind1974.kunits

import kotlinx.serialization.Serializable

@Serializable
abstract class Quantity<T : MeasureUnit>() : Comparable<Quantity<T>> {

    abstract val amount: Double
    abstract val unit: T

    abstract fun create(amount: Double, unit: T) : Quantity<T>

    fun to(unit: T): Quantity<T> {
        val baseUnit = this.unit.convertToBaseUnit(amount)
        return create(unit.convertFromBaseUnit(baseUnit), unit)
    }

    operator fun plus(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount + converted
        return create(amount, this.unit)
    }

    operator fun minus(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount - converted
        return create(amount, this.unit)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Quantity<*>)
            return false
        return this.unit.convertToBaseUnit(amount) == other.unit.convertToBaseUnit(other.amount)
    }

    override fun hashCode() = this.unit.convertToBaseUnit(amount).hashCode()

    override fun toString(): String = "$amount$unit"

    fun toString(decimals: UInt): String = "${toAmountString(decimals)}$unit"

    fun toAmountString(decimals: UInt): String = "${amount.format(decimals)}"

    override fun compareTo(other: Quantity<T>): Int {
        val base = unit.convertToBaseUnit(amount)
        val otherBase = other.unit.convertToBaseUnit(other.amount)
        if (base == otherBase) return 0
        if (base < otherBase) return -1
        return 1
    }
}