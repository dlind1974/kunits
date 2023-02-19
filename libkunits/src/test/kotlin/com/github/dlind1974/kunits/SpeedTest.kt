package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class SpeedTest {

    @Test
    fun speedByNumbers() {
        verify(12.meterPerSecond, 12.0, SpeedUnit.MeterPerSecond)
        verify(12.3.meterPerSecond, 12.3, SpeedUnit.MeterPerSecond)
        verify((-10).meterPerSecond, -10.0, SpeedUnit.MeterPerSecond)
        verify(0.meterPerSecond, 0.0, SpeedUnit.MeterPerSecond)

        verify(3.mps, 3.0, SpeedUnit.MeterPerSecond)
        verify(5.2.kilometerPerHour, 5.2, SpeedUnit.KilometerPerHour)
        verify(27.kmph, 27.0, SpeedUnit.KilometerPerHour)

        assertEquals(3.meterPerSecond, 3.metersPerSecond);
        assertEquals(3.kilometerPerHour, 3.kilometersPerHour);
    }

    @Test
    fun conversionFromNumberToSpeedToOtherUnit() {
        verify(10.meterPerSecond.kilometerPerHour, 36.0, SpeedUnit.KilometerPerHour)
        verify(10.meterPerSecond.kilometersPerHour, 36.0, SpeedUnit.KilometerPerHour)
        verify(10.meterPerSecond.kmph, 36.0, SpeedUnit.KilometerPerHour)
        verify(10.mps.kilometerPerHour, 36.0, SpeedUnit.KilometerPerHour)

        verify((-10).meterPerSecond.kmph, -36.0, SpeedUnit.KilometerPerHour)
        verify(0.meterPerSecond.kmph, 0.0, SpeedUnit.KilometerPerHour)

        verify(3600.kilometerPerHour.meterPerSecond, 1000.0, SpeedUnit.MeterPerSecond)
        verify(3600.kilometerPerHour.metersPerSecond, 1000.0, SpeedUnit.MeterPerSecond)
        verify(3600.kilometerPerHour.mps, 1000.0, SpeedUnit.MeterPerSecond)
    }

    @Test
    fun twoSpeedInstancesWithEqualMeasuresShallBeEqual() {
        assertEquals(1000.mps, 3600.kmph)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        var quantity = Quantity(1000.0, SpeedUnit.MeterPerSecond)
        assertEquals(quantity.toString(1U), "1000.0m/s")

        quantity = Quantity(-27.8, SpeedUnit.KilometerPerHour)
        assertEquals(quantity.toString(1U), "-27.8km/h")
    }

    @Test
    fun qwerty(){
        val d1 = 12.5.kilometers
        val d2 = 2.meters
        val d3 = d1 + d2
        println(d3) // => 12.502km
    }
}