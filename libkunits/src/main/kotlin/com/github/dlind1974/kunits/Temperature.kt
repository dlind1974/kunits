package com.github.dlind1974.kunits

class Temperature(amount: Double, unit: TemperatureUnit) : Quantity<TemperatureUnit>(amount, unit)

val Temperature.celsius get() = this.to(TemperatureUnit.Celsius)
val Temperature.fahrenheit get() = this.to(TemperatureUnit.Fahrenheit)
val Temperature.kelvin get() = this.to(TemperatureUnit.Kelvin)

val Number.celcius: Temperature get() = Temperature(this.toDouble(), TemperatureUnit.Celsius)
val Number.fahrenheit: Temperature get() = Temperature(this.toDouble(), TemperatureUnit.Fahrenheit)
val Number.kelvin: Temperature get() = Temperature(this.toDouble(), TemperatureUnit.Kelvin)


