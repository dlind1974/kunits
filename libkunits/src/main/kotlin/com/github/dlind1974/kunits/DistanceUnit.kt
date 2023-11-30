package com.github.dlind1974.kunits

class DistanceUnit(override val name: String, override val ratio: Double) : MeasureUnit() {
    companion object Factory {
        val Kilometer = DistanceUnit("km", 1000.0)
        val km = Kilometer
        val Meter = DistanceUnit("m", 1.0)
        val m = Meter
        val Centimeter = DistanceUnit("cm", 0.01)
        val cm = Centimeter
        val Millimeter = DistanceUnit("mm", 0.001)
        val mm = Millimeter

        fun fromString(str: String) : DistanceUnit {
            when(str.lowercase()) {
                Kilometer.toString() -> return Kilometer
                Meter.toString() -> return Meter
                Centimeter.toString() -> return Centimeter
                Millimeter.toString() -> return Millimeter
            }
            throw UnitException("Conversion to DistanceUnit from unknown unit: $str")
        }
    }


    override fun toString(): String = name
}
