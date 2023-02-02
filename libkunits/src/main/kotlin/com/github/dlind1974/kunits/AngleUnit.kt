package com.github.dlind1974.kunits

class AngleUnit(name: String, ratio: Double) : MeasureUnit(name, ratio) {
    companion object Factory {
        val Degree = AngleUnit("degree", 1.0)
        val Degrees = AngleUnit("degrees", 1.0)

        fun fromString(str: String) : AngleUnit {
            when(str.lowercase()) {
                Degree.toString() -> return Degree
                Degrees.toString() -> return Degrees
            }
            throw UnitException("Conversion to AngleUnit from unknown unit: $str")
        }
    }

    override fun toString(): String = name
}