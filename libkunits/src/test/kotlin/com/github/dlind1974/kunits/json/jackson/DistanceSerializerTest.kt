package com.github.dlind1974.kunits.json.jackson

import com.github.dlind1974.kunits.Distance
import com.github.dlind1974.kunits.meter
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals

class DistanceSerializerTest {

    @Test
    fun serialize_WhenDistanceIsMeterPerSecond_ProducedJsonIsInMeters() {
        val speedSerializer = DistanceSerializer(Distance::class.java)
        val objectMapper = ObjectMapper()

        val module = SimpleModule("DistanceSerializer", Version(1, 0, 0, null, null, null))
        module.addSerializer(Distance::class.java, speedSerializer)
        objectMapper.registerModule(module)

        val distance = 12.7.meter
        val distanceJson: String = objectMapper.writeValueAsString(distance)
        val expectedDistanceJson = "{\"amount\":12.7,\"unit\":\"m\"}"
        assertEquals(distanceJson, expectedDistanceJson)
    }
}