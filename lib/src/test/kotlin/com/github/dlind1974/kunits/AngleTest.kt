package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class AngleTest {

    @Test
    fun angleByNumbers() {
        verify(12.degree, 12.0, AngleUnit.Degree)
        verify((-10).degree, (-10.0), AngleUnit.Degree)
        verify(0.degree, 0.0, AngleUnit.Degree)
    }

    @Test
    fun degreeAndDegreesAreEqual() {
        assertEquals(12.degrees, 12.degree)
    }

    @Test
    fun toStringShowsCorrectUnit() {
        val quantity = Angle(1000.0, AngleUnit.Degree)
        assertEquals(quantity.toString(1U), "1000.0degree")
        assertEquals(quantity.degrees.toString(1U), "1000.0degrees")
    }
}