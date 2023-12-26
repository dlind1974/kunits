package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TemperatureUnitTest {

    @Test
    fun fromString_WhenStringContainsValidUnit_TemperatureUnitIsReturned() {
        assertEquals(TemperatureUnit.fromString("C"), TemperatureUnit.Celcius)
        assertEquals(TemperatureUnit.fromString("F"), TemperatureUnit.Farenheit)
        assertEquals(TemperatureUnit.fromString("K"), TemperatureUnit.Kelvin)
    }

    @Test
    fun fromString_WhenStringContainsMixedCasing_TemperatureUnitIsReturned() {
        assertEquals(TemperatureUnit.fromString("c"), TemperatureUnit.Celcius)
        assertEquals(TemperatureUnit.fromString("C"), TemperatureUnit.Celcius)
    }

    @Test
    fun fromString_WhenStringContainsUnknownUnit_ExceptionIsThrown() {
        assertFailsWith<UnitException>("Conversion to TemperatureUnit is missing for string: unknown_unit", ) {
            TemperatureUnit.fromString("unknown_unit")
        }
    }
}
