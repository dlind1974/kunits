package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AreaUnitTest {

    @Test
    fun overloadedUnitFactoriesCreateEqualUnits() {
        assertEquals(AreaUnit.SquareMeter, AreaUnit.m2)
        assertEquals(AreaUnit.SquareCentimeter, AreaUnit.cm2)
    }

    @Test
    fun fromString_WhenStringContainsValidUnit_AreaUnitIsReturned() {
        assertEquals(AreaUnit.fromString("m2"), AreaUnit.SquareMeter)
        assertEquals(AreaUnit.fromString("cm2"), AreaUnit.SquareCentimeter)
    }

    @Test
    fun fromString_WhenStringContainsMixedCasing_AreaUnitIsReturned() {
        assertEquals(AreaUnit.fromString("M2"), AreaUnit.SquareMeter)
        assertEquals(AreaUnit.fromString("cM2"), AreaUnit.SquareCentimeter)
    }

    @Test
    fun fromString_WhenStringContainsUnknownUnit_ExceptionIsThrown() {
        assertFailsWith<UnitException>("Conversion to AreaUnit is missing for string: unknown_unit", ) {
            AreaUnit.fromString("unknown_unit")
        }
    }
}