package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SpeedUnitTest {

    @Test
    fun fromString_WhenStringContainsValidUnit_SpeedUnitIsReturned() {
        assertEquals(SpeedUnit.fromString("m/s"), SpeedUnit.MeterPerSecond)
        assertEquals(SpeedUnit.fromString("km/h"), SpeedUnit.KilometerPerHour)
    }

    @Test
    fun fromString_WhenStringContainsMixedCasing_SpeedUnitIsReturned() {
        assertEquals(SpeedUnit.fromString("M/s"), SpeedUnit.MeterPerSecond)
        assertEquals(SpeedUnit.fromString("m/S"), SpeedUnit.MeterPerSecond)
        assertEquals(SpeedUnit.fromString("M/S"), SpeedUnit.MeterPerSecond)
    }

    @Test
    fun fromString_WhenStringContainsUnknownUnit_ExceptionIsThrown() {
        assertFailsWith<UnitException>("Conversion to SpeedUnit is missing for string: unknown_unit", ) {
            SpeedUnit.fromString("unknown_unit")
        }
    }
}
