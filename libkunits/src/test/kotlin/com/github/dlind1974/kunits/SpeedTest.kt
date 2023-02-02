package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class SpeedTest {

    @Test
    fun speedByNumbers() {
        verify(12.meterPerSecond, 12.0, SpeedUnit.MeterPerSecond)
        verify((-10).meterPerSecond, (-10.0), SpeedUnit.MeterPerSecond)
        verify(0.meterPerSecond, 0.0, SpeedUnit.MeterPerSecond)

        verify(3.mps, 3.0, SpeedUnit.MeterPerSecond)
        verify(17.mps, 17.0, SpeedUnit.mps)


        verify(5.kilometerPerHour, 5.0, SpeedUnit.KilometerPerHour)
        verify((-7).kilometerPerHour, (-7.0), SpeedUnit.KilometerPerHour)
        verify(0.kilometerPerHour, 0.0, SpeedUnit.KilometerPerHour)

        verify(27.kmph, 27.0, SpeedUnit.KilometerPerHour)
        verify(15.kmph, 15.0, SpeedUnit.kmph)
    }

    @Test
    fun conversionByNumbers() {
        verify(10.meterPerSecond.kmph, 36.0, SpeedUnit.KilometerPerHour)
        verify((-10).meterPerSecond.kmph, -36.0, SpeedUnit.KilometerPerHour)
        verify(0.meterPerSecond.kmph, 0.0, SpeedUnit.KilometerPerHour)

        verify(3600.kilometerPerHour.mps, 1000.0, SpeedUnit.MeterPerSecond)
        verify((-3600).kilometerPerHour.mps, -1000.0, SpeedUnit.MeterPerSecond)
        verify(0.kilometerPerHour.mps, 0.0, SpeedUnit.MeterPerSecond)
    }

    @Test
    fun twoSpeedInstancesWithEqualMeasuresShallBeEqual() {
        val s1 = 1000.mps
        val s2 = 3600.kmph
        assertEquals(s1, s2)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        var quantity = Quantity(1000.0, SpeedUnit.MeterPerSecond)
        assertEquals(quantity.toString(1U), "1000.0m/s")

        quantity = Quantity(27.8, SpeedUnit.KilometerPerHour)
        assertEquals(quantity.toString(1U), "27.8km/h")
    }
}