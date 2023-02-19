package com.github.dlind1974.kunits.json

import com.github.dlind1974.kunits.Distance
import com.github.dlind1974.kunits.UnitException
import com.github.dlind1974.kunits.meter
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.BeforeTest

class DistanceDeserializerTest {

    private lateinit var module: SimpleModule
    private lateinit var objectMapper: ObjectMapper

    @BeforeTest
    fun setup() {
        module = SimpleModule("DistanceDeserializer", Version(1, 0, 0, null, null, null))
        objectMapper = ObjectMapper()
        module.addDeserializer(Distance::class.java, DistanceDeserializer(Distance::class.java))
        objectMapper.registerModule(module)

    }

    @Test
    fun whenJsonContainsCorrectFialds_DistanceUnitIsReturned() {
        val distanceJson = "{\"amount\":12.7,\"unit\":\"m\"}"
        val distance: Distance = objectMapper.readValue(distanceJson, Distance::class.java)
        assertEquals(distance, 12.7.meter)
    }

    @Test
    fun whenJsonIsMissingAmount_AmountDefaultsToZero() {
        val distanceJson = "{\"unit\":\"m\"}"
        val distance: Distance = objectMapper.readValue(distanceJson, Distance::class.java)
        assertEquals(distance, 0.meter)
    }

    @Test
    fun whenJsonIsUnit_UnitDefaultsToMeter() {
        val distanceJson = "{\"amount\":12.7}"
        val distance: Distance = objectMapper.readValue(distanceJson, Distance::class.java)
        assertEquals(distance, 12.7.meter)
    }

    @Test
    fun whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val distanceJson = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to DistanceUnit is missing for string: not_supported_unit") {
            objectMapper.readValue(distanceJson, Distance::class.java)
        }
    }
}