package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AreaSerializerTest {
    @Test
    fun serialize_WhenAreaUnit_JsonIsInSameUnit() {
        val areaM2 = 12.7.squareMeters
        val areaM2Json = areaM2.serializeToString()
        val expectedAreaM2Json = "{\"amount\":12.7,\"unit\":\"m2\"}"
        assertEquals(expectedAreaM2Json, areaM2Json)

        val areaCm2 = 27.7.squareCentimeters
        val areaCm2Json = areaCm2.serializeToString()
        val expectedAreaCm2Json = "{\"amount\":27.7,\"unit\":\"cm2\"}"
        assertEquals(expectedAreaCm2Json, areaCm2Json)
    }

    @Test
    fun deserialize_WhenJsonContinsCorrectFields_AreaUnitIsReturned() {
        val jsonM2 = "{\"amount\":12.7,\"unit\":\"m2\"}"
        val areaM2: Area = jsonM2.deserializeToArea()
        assertEquals(areaM2, 12.7.squareMeters)

        val jsonCm2 = "{\"amount\":27.5,\"unit\":\"cm2\"}"
        val area: Area = jsonCm2.deserializeToArea()
        assertEquals(area, 27.5.squareCentimeters)
    }

    @Test
    fun deserialize_WhenJsonIsMissingAmount_AmountDefaultsToZero() {
        val json = "{\"unit\":\"m2\"}"
        val area: Area = json.deserializeToArea()
        assertEquals(area, 0.squareMeters)
    }

    @Test
    fun deserialize_WhenJsonIsMissingUnit_ExceptionIsThrown() {
        val json = "{\"amount\":12.7}"
        assertFailsWith<UnitException>("Conversion to AreaUnit from unknown unit: not_supported_unit") {
            json.deserializeToArea()
        }
    }

    @Test
    fun deserialize_whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val json = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to AreaUnit from unknown unit: not_supported_unit") {
            json.deserializeToArea()
        }
    }
}
