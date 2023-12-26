package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals

class TestUnitType(name: String, unitConverter: UnitConverter) : MeasureUnit(name, unitConverter) {
    companion object Factory {
        val Kilo = TestUnitType("Kilo", RatioUnitConverter(1000.0))
        val Base = TestUnitType("Base", RatioUnitConverter(1.0))
        val Centi = TestUnitType("Centi", RatioUnitConverter(0.1))
    }

    override fun toString(): String = name
}

class QuantityTest {
    @Test
    fun convertToUnit() {
        val q = Quantity(3000.0, TestUnitType.Base)
        assertEquals(q.unit, TestUnitType.Base)
        assertEquals(q.to(TestUnitType.Kilo).amount, 3.0)
        assertEquals(q.to(TestUnitType.Centi).amount, 30_000.0)
    }

    @Test
    fun addDifferentUnits() {
        val q1 = Quantity(3.0, TestUnitType.Base)
        val q2 = Quantity(372.0, TestUnitType.Centi)
        val q3 = Quantity(22.4, TestUnitType.Kilo)

        // 3.0 + 37.2 + 22400 = 22440.2
        assertEquals((q1 + q2 + q3).to(TestUnitType.Base).amount, 22_440.2)
    }

    @Test
    fun subtractDifferentUnits() {
        val q1 = Quantity(3.0, TestUnitType.Base)
        val q2 = Quantity(372.0, TestUnitType.Centi)
        val q3 = Quantity(22.4, TestUnitType.Kilo)

        // 3.0 - 37.2 - 22400 = 22440.2
        assertEquals((q1 - q2 - q3).to(TestUnitType.Base).amount, -22_434.2)
    }

    @Test
    fun twoQuantitiesWhereAmountAndUnitSumsToTheSameBaseUnitValueAreEqual() {
        val q1 = Quantity(3.0, TestUnitType.Base)
        val q2 = Quantity(30.0, TestUnitType.Centi)
        assertEquals(q1, q2)
        assertEquals(q1.hashCode(), q2.hashCode())
    }

    @Test
    fun quantitiesIsSortedByBaseUnitAmount() {
        var quantities = mutableListOf(
            Quantity(27.0, TestUnitType.Kilo),
            Quantity(25000.0, TestUnitType.Base),
            Quantity(260000.0, TestUnitType.Centi)
        )
        quantities.sort()
        assertEquals(quantities[0], Quantity(25000.0, TestUnitType.Base))
        assertEquals(quantities[1], Quantity(260000.0, TestUnitType.Centi))
        assertEquals(quantities[2], Quantity(27.0, TestUnitType.Kilo))
    }

    @Test
    fun toStringWithSpecifiedNumberOfDecimals() {
        var quantity = Quantity(27.1234, TestUnitType.Kilo)
        assertEquals(quantity.toString(), "27.1234Kilo")
        assertEquals(quantity.toString(decimals = 2U), "27.12Kilo")
        assertEquals(quantity.toString(decimals = 1U), "27.1Kilo")
        assertEquals(quantity.toString(decimals = 0U), "27Kilo")

        var quantityRoundUp = Quantity(27.5555, TestUnitType.Kilo)
        assertEquals(quantityRoundUp.toString(), "27.5555Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 2U), "27.56Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 1U), "27.6Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 0U), "28Kilo")
    }
}