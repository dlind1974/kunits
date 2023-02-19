package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DistanceUnitTest {
    @Test
    fun overloadedUnitFactoriesCreateEqualUnits() {
        assertEquals(DistanceUnit.Kilometer, DistanceUnit.km)
        assertEquals(DistanceUnit.Meter, DistanceUnit.m)
        assertEquals(DistanceUnit.Centimeter, DistanceUnit.cm)
        assertEquals(DistanceUnit.Millimeter, DistanceUnit.mm)
    }

    @Test
    fun fromString_WhenStringContainsValidUnit_DistanceUnitIsReturned() {
        assertEquals(DistanceUnit.fromString("km"), DistanceUnit.Kilometer)
        assertEquals(DistanceUnit.fromString("m"), DistanceUnit.Meter)
        assertEquals(DistanceUnit.fromString("cm"), DistanceUnit.Centimeter)
        assertEquals(DistanceUnit.fromString("mm"), DistanceUnit.Millimeter)
    }

    @Test
    fun fromString_WhenStringContainsMixedCasing_DistanceUnitIsReturned() {
        assertEquals(DistanceUnit.fromString("kM"), DistanceUnit.Kilometer)
        assertEquals(DistanceUnit.fromString("Km"), DistanceUnit.Kilometer)
        assertEquals(DistanceUnit.fromString("M"), DistanceUnit.Meter)
        assertEquals(DistanceUnit.fromString("cM"), DistanceUnit.Centimeter)
        assertEquals(DistanceUnit.fromString("Cm"), DistanceUnit.Centimeter)
        assertEquals(DistanceUnit.fromString("mM"), DistanceUnit.Millimeter)
        assertEquals(DistanceUnit.fromString("Mm"), DistanceUnit.Millimeter)
    }

    @Test
    fun fromString_WhenStringContainsUnknownUnit_ExceptionIsThrown() {
        assertFailsWith<UnitException>("Conversion to DistanceUnit is missing for string: unknown_unit", ) {
            DistanceUnit.fromString("unknown_unit")
        }
    }
}