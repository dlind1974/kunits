package com.github.dlind1974.kunits.json

import com.github.dlind1974.kunits.Speed
import com.github.dlind1974.kunits.UnitException
import com.github.dlind1974.kunits.meterPerSecond
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.BeforeTest

class SpeedDeserializerTest {

    private lateinit var module: SimpleModule
    private lateinit var mapper: ObjectMapper

    @BeforeTest
    fun setup() {
        module = SimpleModule("SpeedDeserializer", Version(1, 0, 0, null, null, null))
        mapper = ObjectMapper()
        module.addDeserializer(Speed::class.java, SpeedDeserializer(Speed::class.java))
        mapper.registerModule(module)

    }

    @Test
    fun whenJsonContainsCorrectFialds_SpeedUnitIsReturned() {
        val speedJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
        val speed: Speed = mapper.readValue(speedJson, Speed::class.java)
        assertEquals(speed, 12.7.meterPerSecond)
    }

    @Test
    fun whenJsonIsMissingAmount_AmountDefaultsToZero() {
        val speedJson = "{\"unit\":\"m/s\"}"
        val speed: Speed = mapper.readValue(speedJson, Speed::class.java)
        assertEquals(speed, 0.meterPerSecond)
    }

    @Test
    fun whenJsonIsUnit_UnitDefaultsToMetersPerSecond() {
        val speedJson = "{\"amount\":12.7}"
        val speed: Speed = mapper.readValue(speedJson, Speed::class.java)
        assertEquals(speed, 12.7.meterPerSecond)
    }

    @Test
    fun whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val speedJson = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to SpeedUnit is missing for string: not_supported_unit") {
            mapper.readValue(speedJson, Speed::class.java)
        }
    }
}