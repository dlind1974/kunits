package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AngleUnitTest {

    @Test
    fun fromString_WhenStringContainsValidUnit_AngleUnitIsReturned() {
        assertEquals(AngleUnit.fromString("degree"), AngleUnit.Degree)
        assertEquals(AngleUnit.fromString("degrees"), AngleUnit.Degrees)
    }

    @Test
    fun fromString_WhenStringContainsMixedCasing_AngleUnitIsReturned() {
        assertEquals(AngleUnit.fromString("Degree"), AngleUnit.Degree)
    }

    @Test
    fun fromString_WhenStringContainsUnknownUnit_ExceptionIsThrown() {
        assertFailsWith<UnitException>("Conversion to AngleUnit is missing for string: unknown_unit") {
            AngleUnit.fromString("unknown_unit")
        }
    }
}
