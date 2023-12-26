package com.github.dlind1974.kunits

class DistanceUnit(name: String, unitConverter: UnitConverter) : MeasureUnit(name, unitConverter) {
    companion object Factory {
        val Kilometer = DistanceUnit("km", RatioUnitConverter(1000.0))
        val km = Kilometer
        val Meter = DistanceUnit("m", RatioUnitConverter(1.0))
        val m = Meter
        val Centimeter = DistanceUnit("cm", RatioUnitConverter(0.01))
        val cm = Centimeter
        val Millimeter = DistanceUnit("mm", RatioUnitConverter(0.001))
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
