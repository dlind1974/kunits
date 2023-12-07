package com.github.dlind1974.kunits.json.jackson

import com.github.dlind1974.kunits.Angle
import com.github.dlind1974.kunits.degrees
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals

class AngleSerializerTest {

    @Test
    fun serialize_WhenAngleIsDegrees_ProducedJsonIsInDegrees() {
        val angleSerializer = AngleSerializer(Angle::class.java)
        val objectMapper = ObjectMapper()

        val module = SimpleModule("AngleSerializer", Version(1, 0, 0, null, null, null))
        module.addSerializer(Angle::class.java, angleSerializer)
        objectMapper.registerModule(module)

        val angle = 275.4.degrees
        val angleJson: String = objectMapper.writeValueAsString(angle)
        val expectedAngleJson = "{\"amount\":275.4,\"unit\":\"degrees\"}"
        assertEquals(angleJson, expectedAngleJson)
    }
}