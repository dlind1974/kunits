package com.github.dlind1974.kunits

import kotlin.test.Test
import kotlin.test.assertEquals



class TestUnitType(override val name: String, override val ratio: Double) : MeasureUnit() {
    companion object Factory {
        val Kilo = TestUnitType("Kilo", 1000.0)
        val Base = TestUnitType("Base", 1.0)
        val Centi = TestUnitType("Centi", 0.1)
    }

    override fun toString(): String = name
}

class TestQuantity(override val amount: Double, override val unit: TestUnitType) : Quantity<TestUnitType>() {
    override fun create(amount: Double, unit: TestUnitType): Quantity<TestUnitType> {
        return TestQuantity(amount, unit)
    }
}

class QuantityTest {
    @Test
    fun convertToUnit() {
        val q = TestQuantity(3000.0, TestUnitType.Base)
        assertEquals(q.unit, TestUnitType.Base)
        assertEquals(q.to(TestUnitType.Kilo).amount, 3.0)
        assertEquals(q.to(TestUnitType.Centi).amount, 30_000.0)
    }

    @Test
    fun addDifferentUnits() {
        val q1 = TestQuantity(3.0, TestUnitType.Base)
        val q2 = TestQuantity(372.0, TestUnitType.Centi)
        val q3 = TestQuantity(22.4, TestUnitType.Kilo)

        // 3.0 + 37.2 + 22400 = 22440.2
        assertEquals((q1 + q2 + q3).to(TestUnitType.Base).amount, 22_440.2)
    }

    @Test
    fun subtractDifferentUnits() {
        val q1 = TestQuantity(3.0, TestUnitType.Base)
        val q2 = TestQuantity(372.0, TestUnitType.Centi)
        val q3 = TestQuantity(22.4, TestUnitType.Kilo)

        // 3.0 - 37.2 - 22400 = 22440.2
        assertEquals((q1 - q2 - q3).to(TestUnitType.Base).amount, -22_434.2)
    }

    @Test
    fun twoQuantitiesWhereAmountAndUnitSumsToTheSameBaseUnitValueAreEqual() {
        val q1 = TestQuantity(3.0, TestUnitType.Base)
        val q2 = TestQuantity(30.0, TestUnitType.Centi)
        assertEquals(q1, q2)
        assertEquals(q1.hashCode(), q2.hashCode())
    }

    @Test
    fun quantitiesIsSortedByBaseUnitAmount() {
        var quantities = mutableListOf(
            TestQuantity(27.0, TestUnitType.Kilo),
            TestQuantity(25000.0, TestUnitType.Base),
            TestQuantity(260000.0, TestUnitType.Centi)
        )
        quantities.sort()
        assertEquals(quantities[0], TestQuantity(25000.0, TestUnitType.Base))
        assertEquals(quantities[1], TestQuantity(260000.0, TestUnitType.Centi))
        assertEquals(quantities[2], TestQuantity(27.0, TestUnitType.Kilo))
    }

    @Test
    fun toStringWithSpecifiedNumberOfDecimals() {
        var quantity = TestQuantity(27.1234, TestUnitType.Kilo)
        assertEquals(quantity.toString(), "27.1234Kilo")
        assertEquals(quantity.toString(decimals = 2U), "27.12Kilo")
        assertEquals(quantity.toString(decimals = 1U), "27.1Kilo")
        assertEquals(quantity.toString(decimals = 0U), "27Kilo")

        var quantityRoundUp = TestQuantity(27.5555, TestUnitType.Kilo)
        assertEquals(quantityRoundUp.toString(), "27.5555Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 2U), "27.56Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 1U), "27.6Kilo")
        assertEquals(quantityRoundUp.toString(decimals = 0U), "28Kilo")
    }
}