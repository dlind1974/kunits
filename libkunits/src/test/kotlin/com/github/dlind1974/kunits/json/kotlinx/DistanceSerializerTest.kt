package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DistanceSerializerTest {
    @Test
    fun serialize_WhenDistanceUnit_JsonIsInSameUnit() {
        val distanceMeters = 12.7.meters
        val distanceMetersJson = distanceMeters.serializeToString()
        val expectedDistanceMpsJson = "{\"amount\":12.7,\"unit\":\"m\"}"
        assertEquals(expectedDistanceMpsJson, distanceMetersJson)

        val distanceKilometers = 27.7.kilometer
        val distanceKilometersJson = distanceKilometers.serializeToString()
        val expectedDistanceKphJson = "{\"amount\":27.7,\"unit\":\"km\"}"
        assertEquals(expectedDistanceKphJson, distanceKilometersJson)
    }

    @Test
    fun deserialize_WhenJsonContinsCorrectFields_DistanceUnitIsReturned() {
        val jsonMps = "{\"amount\":12.7,\"unit\":\"m\"}"
        val distanceMeters: Distance =  jsonMps.deserializeToDistance()
        assertEquals(distanceMeters, 12.7.meters)

        val jsonKph = "{\"amount\":27.5,\"unit\":\"km\"}"
        val speed: Distance =  jsonKph.deserializeToDistance()
        assertEquals(speed, 27.5.kilometer)
    }

    @Test
    fun deserialize_WhenJsonIsMissingAmount_AmountDefaultsToZero() {
        val json = "{\"unit\":\"m\"}"
        val speed: Distance =  json.deserializeToDistance()
        assertEquals(speed, 0.meters)
    }

    @Test
    fun deserialize_WhenJsonIsMissingUnit_ExceptionIsThrown() {
        val json = "{\"amount\":12.7}"
        assertFailsWith<UnitException>("Conversion to DistanceUnit from unknown unit: not_supported_unit") {
            json.deserializeToDistance()
        }
    }

    @Test
    fun deserialize_whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val json = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to DistanceUnit from unknown unit: not_supported_unit") {
            json.deserializeToDistance()
        }
    }
}
