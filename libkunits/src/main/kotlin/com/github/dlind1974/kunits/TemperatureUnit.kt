package com.github.dlind1974.kunits

val toCelsiusFromFahrenheit = {
    fahrenheit: Double -> (fahrenheit-32) * (5.0/9.0)
}

val toFahrenheitFromCelsius = {
    celsius: Double -> celsius*(9.0/5.0) + 32
}

val toCelsiusFromKelvin = {
        kelvin: Double -> kelvin - 273.15
}

val toKelvinFromCelsius = {
        celsius: Double -> celsius + 273.15
}

class TemperatureUnit(name: String, unitConverter: UnitConverter) : MeasureUnit(name, unitConverter) {
    companion object Factory {
        val Celsius = TemperatureUnit("C", RatioUnitConverter(1.0))
        val Fahrenheit = TemperatureUnit("F", FormulaUnitConverter(toCelsiusFromFahrenheit, toFahrenheitFromCelsius) )
        val Kelvin = TemperatureUnit("K", FormulaUnitConverter(toCelsiusFromKelvin, toKelvinFromCelsius))

        fun fromString(str:String) : TemperatureUnit {
            when(str.lowercase()) {
                Celsius.toString().lowercase() -> return Celsius
                Fahrenheit.toString().lowercase() -> return Fahrenheit
                Kelvin.toString().lowercase() -> return Kelvin
            }
            throw UnitException("Conversion to TemperatureUnit from unknown unit: $str")
        }
    }

    override fun toString(): String = name
}