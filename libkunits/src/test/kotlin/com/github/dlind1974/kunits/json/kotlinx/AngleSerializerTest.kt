package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AngleSerializerTest {
    @Test
    fun serialize_WhenAngleUnit_JsonIsInSameUnit() {
        val angleDegrees = 12.7.degrees
        val angleDegreesJson = angleDegrees.serializeToString()
        val expectedAngleDegreesJson = "{\"amount\":12.7,\"unit\":\"degrees\"}"
        assertEquals(expectedAngleDegreesJson, angleDegreesJson)
    }

    @Test
    fun deserialize_WhenJsonContinsCorrectFields_AngleUnitIsReturned() {
        val jsonDegrees = "{\"amount\":12.7,\"unit\":\"degrees\"}"
        val angleDegrees: Angle = jsonDegrees.deserializeToAngle()
        assertEquals(angleDegrees, 12.7.degrees)
    }

    @Test
    fun deserialize_WhenJsonIsMissingAmount_AmountDefaultsToZero() {
        val json = "{\"unit\":\"degrees\"}"
        val angle: Angle = json.deserializeToAngle()
        assertEquals(angle, 0.degrees)
    }

    @Test
    fun deserialize_WhenJsonIsMissingUnit_ExceptionIsThrown() {
        val json = "{\"amount\":12.7}"
        assertFailsWith<UnitException>("Conversion to AngleUnit from unknown unit: not_supported_unit") {
            json.deserializeToAngle()
        }
    }

    @Test
    fun deserialize_whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val json = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to AngleUnit from unknown unit: not_supported_unit") {
            json.deserializeToAngle()
        }
    }
}