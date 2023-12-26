package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class TemperatureTest {

    @Test
    fun temperatureByNumbers() {
        verify(12.celcius, 12.0, TemperatureUnit.Celsius)
        verify(12.3.celcius, 12.3, TemperatureUnit.Celsius)
        verify((-10).celcius, -10.0, TemperatureUnit.Celsius)
        verify(0.celcius, 0.0, TemperatureUnit.Celsius)

        verify(5.2.fahrenheit, 5.2, TemperatureUnit.Fahrenheit)
        verify(27.fahrenheit, 27.0, TemperatureUnit.Fahrenheit)
        verify((-5.2).kelvin, (-5.2), TemperatureUnit.Kelvin)
        verify((-27).kelvin, (-27.0), TemperatureUnit.Kelvin)
    }

    @Test
    fun conversionFromNumberToTemperatureToOtherUnit() {
        verify(10.celcius.fahrenheit, 50.0, TemperatureUnit.Fahrenheit)
        verify(10.celcius.kelvin, 283.15, TemperatureUnit.Kelvin)
        verify(50.fahrenheit.celsius, 10.0, TemperatureUnit.Celsius)
        verify(50.fahrenheit.kelvin, 283.15, TemperatureUnit.Kelvin)
        verify((-27).celcius.fahrenheit, (-16.6), TemperatureUnit.Fahrenheit)
    }

    @Test
    fun twoTemperatureInstancesWithEqualMeasuresShallBeEqual() {
        assertEquals(10.celcius, 50.0.fahrenheit)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        var quantity = Quantity(1000.0, TemperatureUnit.Celsius)
        assertEquals(quantity.toString(1U), "1000.0C")

        quantity = Quantity(-27.8, TemperatureUnit.Fahrenheit)
        assertEquals(quantity.toString(1U), "-27.8F")

        quantity = Quantity(37.5, TemperatureUnit.Kelvin)
        assertEquals(quantity.toString(1U), "37.5K")
    }
}