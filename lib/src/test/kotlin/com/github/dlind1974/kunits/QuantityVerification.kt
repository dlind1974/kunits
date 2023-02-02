package com.github.dlind1974.kunits

import kotlin.test.assertEquals

internal fun <T : MeasureUnit> verify(
    quantity: Quantity<T>,
    expectedAmount: Double,
    expectedUnit: T
) {
    assertEquals(quantity.amount, expectedAmount)
    assertEquals(quantity.unit, expectedUnit)
}