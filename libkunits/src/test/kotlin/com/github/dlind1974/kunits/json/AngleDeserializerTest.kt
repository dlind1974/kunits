package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.dlind1974.kunits.Angle
import com.github.dlind1974.kunits.UnitException
import com.github.dlind1974.kunits.degrees
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.BeforeTest


class AngleDeserializerTest {

    private lateinit var module: SimpleModule
    private lateinit var mapper: ObjectMapper

    @BeforeTest
    fun setup() {
            module = SimpleModule("AngleDeserializer", Version(1, 0, 0, null, null, null))
            mapper = ObjectMapper()
            module.addDeserializer(Angle::class.java, AngleDeserializer(Angle::class.java))
            mapper.registerModule(module)

    }

    @Test
    fun whenJsonContainsCorrectFialds_AngleUnitIsReturned() {
        val angleJson = "{\"amount\":275,\"unit\":\"degrees\"}"
        val angle: Angle = mapper.readValue(angleJson, Angle::class.java)
        assertEquals(angle, 275.degrees)
    }

    @Test
    fun whenJsonIsMissingAmount_AmountDefaultsToZero() {
        val angleJson = "{\"unit\":\"degrees\"}"
        val angle: Angle = mapper.readValue(angleJson, Angle::class.java)
        assertEquals(angle, 0.degrees)
    }

    @Test
    fun whenJsonIsUnit_UnitDefaultsToDegrees() {
        val angleJson = "{\"amount\":275}"
        val angle: Angle = mapper.readValue(angleJson, Angle::class.java)
        assertEquals(angle, 275.degrees)
    }

    @Test
    fun whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val angleJson = "{\"amount\":275,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to AngleUnit is missing for string: not_supported_unit") {
            mapper.readValue(angleJson, Angle::class.java)
        }
    }
}