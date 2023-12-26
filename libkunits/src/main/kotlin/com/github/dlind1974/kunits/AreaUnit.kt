package com.github.dlind1974.kunits

class AreaUnit(name: String, unitConverter: UnitConverter) : MeasureUnit(name, unitConverter) {

    companion object Factory {
        val SquareMeter = AreaUnit("m2", RatioUnitConverter(1.0))
        val m2 = SquareMeter
        val SquareCentimeter = AreaUnit("cm2", RatioUnitConverter(0.0001))
        val cm2 = SquareCentimeter

        fun fromString(str:String) : AreaUnit {
            when(str.lowercase()) {
                AreaUnit.SquareMeter.toString() -> return AreaUnit.SquareMeter
                AreaUnit.SquareCentimeter.toString() -> return AreaUnit.SquareCentimeter
            }
            throw UnitException("Conversion to AreaUnit from unknown unit: $str")
        }
    }


    override fun toString(): String = name
}