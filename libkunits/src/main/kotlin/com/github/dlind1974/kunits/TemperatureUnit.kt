package com.github.dlind1974.kunits


val toCelciusFromFarenheit = {
    farenheit: Double -> (5.0/9.0)*(farenheit-32)
}

val toFarenheitFromCelcius = {
    celcius: Double -> (5.0/9.0)*(celcius-32)
}

val toCelciusFromKelvin = {
        kelvin: Double -> kelvin - 273.15
}

val toKelvinFromCelcius = {
        celcius: Double -> celcius + 273.15
}

class TemperatureUnit(name: String, unitConverter: UnitConverter) : MeasureUnit(name, unitConverter) {
    companion object Factory {
        val Celcius = TemperatureUnit("C", RatioUnitConverter(1.0))
        val Farenheit = TemperatureUnit("F", FormulaUnitConverter(toCelciusFromFarenheit, toFarenheitFromCelcius) )
        val Kelvin = TemperatureUnit("K", FormulaUnitConverter(toCelciusFromKelvin, toKelvinFromCelcius))

        fun fromString(str:String) : TemperatureUnit {
            when(str.lowercase()) {
                Celcius.toString().lowercase() -> return Celcius
                Farenheit.toString().lowercase() -> return Farenheit
                Kelvin.toString().lowercase() -> return Kelvin
            }
            throw UnitException("Conversion to SpeedUnit from unknown unit: $str")
        }
    }

    override fun toString(): String = name
}