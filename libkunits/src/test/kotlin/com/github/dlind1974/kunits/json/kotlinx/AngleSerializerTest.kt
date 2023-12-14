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

    /*
    @Test
    fun deserialize_WhenJsonContinsCorrectFields_SpeedUnitIsReturned() {
        val jsonMps = "{\"amount\":12.7,\"unit\":\"m/s\"}"
        val speedMps: Speed =  jsonMps.deserializeToSpeed()
        assertEquals(speedMps, 12.7.meterPerSecond)

        val jsonKph = "{\"amount\":27.5,\"unit\":\"km/h\"}"
        val speed: Speed =  jsonKph.deserializeToSpeed()
        assertEquals(speed, 27.5.kilometerPerHour)
    }

    @Test
    fun deserialize_WhenJsonIsMissingAmount_AmountDefaultsToZero() {
        val json = "{\"unit\":\"m/s\"}"
        val speed: Speed =  json.deserializeToSpeed()
        assertEquals(speed, 0.meterPerSecond)
    }

    @Test
    fun deserialize_WhenJsonIsMissingUnit_ExceptionIsThrown() {
        val json = "{\"amount\":12.7}"
        assertFailsWith<UnitException>("Conversion to SpeedUnit from unknown unit: not_supported_unit") {
            json.deserializeToSpeed()
        }
    }

    @Test
    fun deserialize_whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val json = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to SpeedUnit from unknown unit: not_supported_unit") {
            json.deserializeToSpeed()
        }
    }

     */
}