package com.github.dlind1974.kunits.json

import com.github.dlind1974.kunits.Speed
import com.github.dlind1974.kunits.meterPerSecond
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals

class SpeedSerializerTest {

    @Test
    fun serialize_WhenSpeedIsMeterPerSecond_ProducedJsonIsInMeterPerSecond() {
        val speedSerializer = SpeedSerializer(Speed::class.java)
        val objectMapper = ObjectMapper()

        val module = SimpleModule("SpeedSerializer", Version(1, 0, 0, null, null, null))
        module.addSerializer(Speed::class.java, speedSerializer)
        objectMapper.registerModule(module)

        val speed = 12.7.meterPerSecond
        val speedJson: String = objectMapper.writeValueAsString(speed)
        val expectedSpeedJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
        assertEquals(speedJson, expectedSpeedJson)
    }
}