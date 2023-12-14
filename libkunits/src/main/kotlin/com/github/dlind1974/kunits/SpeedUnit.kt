package com.github.dlind1974.kunits


const val kmphToMpsRatio = 1000.0/(60.0 * 60.0)

class SpeedUnit(override val name: String, override val ratio: Double = 1.0) : MeasureUnit() {
    companion object Factory {
        val MeterPerSecond = SpeedUnit("m/s", 1.0)
        val KilometerPerHour = SpeedUnit("km/h", kmphToMpsRatio)
        val mps = MeterPerSecond
        val kmph = KilometerPerHour

        fun fromString(str:String) : SpeedUnit {
            when(str.lowercase()) {
                MeterPerSecond.toString() -> return MeterPerSecond
                KilometerPerHour.toString() -> return KilometerPerHour
            }
            throw UnitException("Conversion to SpeedUnit from unknown unit: $str")
        }
    }

    override fun toString(): String = name
}