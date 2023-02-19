package com.github.dlind1974.kunits.json

import com.github.dlind1974.kunits.Area
import com.github.dlind1974.kunits.squareMeters
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals

class AreaSerializerTest {

    @Test
    fun serialize_WhenSpeedIsMeterPerSecond_ProducedJsonIsInMeterPerSecond() {
        val areaSerializer = AreaSerializer(Area::class.java)
        val objectMapper = ObjectMapper()

        val module = SimpleModule("SpeedSerializer", Version(1, 0, 0, null, null, null))
        module.addSerializer(Area::class.java, areaSerializer)
        objectMapper.registerModule(module)

        val speed = 12.7.squareMeters
        val speedJson: String = objectMapper.writeValueAsString(speed)
        val expectedSpeedJson = "{\"amount\":12.7,\"unit\":\"m2\"}"
        assertEquals(speedJson, expectedSpeedJson)
    }
}