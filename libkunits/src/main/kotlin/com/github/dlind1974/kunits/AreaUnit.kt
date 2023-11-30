package com.github.dlind1974.kunits

class AreaUnit(override val name: String, override val ratio: Double) : MeasureUnit() {

    companion object Factory {
        val SquareMeter = AreaUnit("m2", 1.0)
        val m2 = SquareMeter
        val SquareCentimeter = AreaUnit("cm2", 0.0001)
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