package com.github.dlind1974.kunits.json

import com.github.dlind1974.kunits.Area
import com.github.dlind1974.kunits.m2
import com.github.dlind1974.kunits.UnitException
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.BeforeTest

class AreaDeserializerTest {

    private lateinit var module: SimpleModule
    private lateinit var objectMapper: ObjectMapper

    @BeforeTest
    fun setup() {
        module = SimpleModule("AreaDeserializer", Version(1, 0, 0, null, null, null))
        objectMapper = ObjectMapper()
        module.addDeserializer(Area::class.java, AreaDeserializer(Area::class.java))
        objectMapper.registerModule(module)

    }

    @Test
    fun whenJsonContainsCorrectFialds_AreaUnitIsReturned() {
        val areaJson = "{\"amount\":12.7,\"unit\":\"m2\"}"
        val area: Area = objectMapper.readValue(areaJson, Area::class.java)
        assertEquals(area, 12.7.m2)
    }

    @Test
    fun whenJsonIsMissingAmount_AmountDefaultsToZero() {
        val areaJson = "{\"unit\":\"m2\"}"
        val area: Area = objectMapper.readValue(areaJson, Area::class.java)
        assertEquals(area, 0.m2)
    }

    @Test
    fun whenJsonIsUnit_UnitDefaultsToSquareMeters() {
        val areaJson = "{\"amount\":12.7}"
        val area: Area = objectMapper.readValue(areaJson, Area::class.java)
        assertEquals(area, 12.7.m2)
    }

    @Test
    fun whenJsonContainsNotSupportedUnit_ExceptionIsThrow() {
        val areaJson = "{\"amount\":12.7,\"unit\":\"not_supported_unit\"}"
        assertFailsWith<UnitException>("Conversion to AreaUnit is missing for string: not_supported_unit") {
            objectMapper.readValue(areaJson, Area::class.java)
        }
    }
}