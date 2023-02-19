package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class AreaTest {
    @Test
    fun areaByNumbers() {
        verify(12.squareMeters, 12.0, AreaUnit.SquareMeter)
        verify(12.3.squareMeters, 12.3, AreaUnit.SquareMeter)
        verify((-12).squareMeters, -12.0, AreaUnit.SquareMeter)
        verify(0.squareMeters, 0.0, AreaUnit.SquareMeter)

        verify((3.75).m2, 3.75, AreaUnit.SquareMeter)
        verify((-3.75).squareCentimeters, -3.75, AreaUnit.SquareCentimeter)
        verify((-3.75).cm2, -3.75, AreaUnit.SquareCentimeter)

        assertEquals(3.squareMeters, 3.squareMeter);
        assertEquals(3.squareCentimeters, 3.squareCentimeter);
    }

    @Test
    fun conversionFromNumberToAreaToOtherUnit() {
        verify(12.squareMeters.squareCentimeter, 120_000.0, AreaUnit.SquareCentimeter)
        verify(12.squareMeters.squareCentimeters, 120_000.0, AreaUnit.SquareCentimeter)
        verify(12.squareMeters.cm2, 120_000.0, AreaUnit.SquareCentimeter)
        verify(12.m2.cm2, 120_000.0, AreaUnit.SquareCentimeter)

        verify((-12).squareMeters.squareCentimeters, -120_000.0, AreaUnit.SquareCentimeter)
        verify((0).squareMeters.squareCentimeters, 0.0, AreaUnit.SquareCentimeter)

        verify(11_301.squareCentimeters.squareMeter, 1.1301, AreaUnit.SquareMeter)
        verify(11_301.squareCentimeters.squareMeters, 1.1301, AreaUnit.SquareMeter)
        verify(11_301.squareCentimeters.m2, 1.1301, AreaUnit.SquareMeter)
    }

    @Test
    fun twoAreaInstancesWithEqualMeasuresAreEqual() {
        assertEquals(1.1301.m2, 11_301.cm2)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        var quantity = Quantity(1000.0, AreaUnit.SquareMeter)
        assertEquals(quantity.toString(1U), "1000.0m2")

        quantity = Quantity(-27.8, AreaUnit.SquareCentimeter)
        assertEquals(quantity.toString(1U), "-27.8cm2")
    }
}