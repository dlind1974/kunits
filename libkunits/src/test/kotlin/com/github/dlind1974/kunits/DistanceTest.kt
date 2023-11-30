package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class DistanceTest {
    @Test
    fun distanceByNumbers() {
        verify(12.kilometers, 12.0, DistanceUnit.Kilometer)
        verify(12.3.kilometers, 12.3, DistanceUnit.Kilometer)
        verify((-12).kilometers, -12.0, DistanceUnit.Kilometer)
        verify(0.kilometers, 0.0, DistanceUnit.Kilometer)

        verify(17.km, 17.0, DistanceUnit.Kilometer)
        verify(17.meters, 17.0, DistanceUnit.Meter)
        verify(17.m, 17.0, DistanceUnit.Meter)
        verify(17.centimeters, 17.0, DistanceUnit.Centimeter)
        verify(17.cm, 17.0, DistanceUnit.Centimeter)
        verify(17.millimeters, 17.0, DistanceUnit.Millimeter)
        verify(17.mm, 17.0, DistanceUnit.Millimeter)

        assertEquals(3.kilometers, 3.kilometer);
        assertEquals(3.meters, 3.meter);
        assertEquals(3.centimeters, 3.centimeter);
        assertEquals(3.millimeters, 3.millimeter);
    }

    @Test
    fun conversionFromNumberToDistanceToOtherUnit() {
        verify(12.kilometers.meter, 12_000.0, DistanceUnit.Meter)
        verify(12.kilometers.meters, 12_000.0, DistanceUnit.Meter)
        verify(12.kilometers.m, 12_000.0, DistanceUnit.Meter)
        verify(12.km.meters, 12_000.0, DistanceUnit.Meter)

        verify((-12).kilometers.meter, -12_000.0, DistanceUnit.Meter)
        verify(0.kilometers.meter, 0.0, DistanceUnit.Meter)

        verify(12.meters.kilometers, 0.012, DistanceUnit.Kilometer)
        verify(12.meters.kilometer, 0.012, DistanceUnit.Kilometer)
        verify(12.meters.km, 0.012, DistanceUnit.Kilometer)

        verify((-23.23).centimeters.millimeters, -232.3, DistanceUnit.Millimeter)
        verify((-23.23).centimeters.millimeter, -232.3, DistanceUnit.Millimeter)
        verify((-23.23).centimeters.mm, -232.3, DistanceUnit.Millimeter)

        verify(13.3.millimeters.centimeters, 1.33, DistanceUnit.Centimeter)
        verify(13.3.millimeters.centimeter, 1.33, DistanceUnit.Centimeter)
        verify(13.3.millimeters.cm, 1.33, DistanceUnit.Centimeter)
    }

    @Test
    fun twoDistanceInstancesWithEqualMeasuresShallBeEqual() {
        assertEquals(2.6.m, 0.0026.km)
        assertEquals(2.6.m, 260.cm)
        assertEquals(2.6.m, 2600.mm)
        assertEquals(2.6.km, 260_000.cm)
        assertEquals(2.6.km, 2_600_000.mm)
        assertEquals(2.6.cm, 26.mm)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        var quantity = Distance(1000.0, DistanceUnit.Kilometer)
        assertEquals(quantity.toString(1U), "1000.0km")

        quantity = Distance(-1000.0, DistanceUnit.Meter)
        assertEquals(quantity.toString(1U), "-1000.0m")

        quantity = Distance(1000.0, DistanceUnit.Centimeter)
        assertEquals(quantity.toString(1U), "1000.0cm")

        quantity = Distance(1000.0, DistanceUnit.Millimeter)
        assertEquals(quantity.toString(1U), "1000.0mm")


    }

}
