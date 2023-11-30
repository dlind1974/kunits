package com.github.dlind1974.kunits


class AngleUnit(override val name: String, override val ratio: Double) : MeasureUnit() {
    companion object Factory {
        val Degree = AngleUnit("degree", 1.0)

        // To not re-use Degree above is on purpose. This in order to have a natural unit text when serializing, i.e.
        // 1degree and 2degrees. This differs from other units like distance which uses a shorthand for the unit text
        // like "km" which does not distinguish between plural and singular
        val Degrees = AngleUnit("degrees", 1.0)

        fun fromString(str: String): AngleUnit {
            when (str.lowercase()) {
                Degree.toString() -> return Degree
                Degrees.toString() -> return Degrees
            }
            throw UnitException("Conversion to AngleUnit from unknown unit: $str")
        }
    }

    override fun toString(): String = name
}