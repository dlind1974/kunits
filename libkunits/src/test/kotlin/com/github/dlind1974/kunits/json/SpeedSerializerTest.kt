package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.dlind1974.kunits.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

@Serializable
open class Project(val name: String, val owner: String)

class OwnedProject(name: String, owner: String) : Project(name, owner)

fun main() {
    val data: Project = OwnedProject("kotlinx.coroutines", "kotlin")
    println(Json.encodeToString(data))

    // Cannot be done via inherited class
    val data2: OwnedProject = OwnedProject("kotlinx.coroutines", "kotlin")
    println(Json.encodeToString(data2))

}

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
    
  @Test
  fun speedUnit() {
      val speedUnit = SpeedUnit.MeterPerSecond
      val speedUnitJson : String = Json.encodeToString(speedUnit);
      val expectedJson = "{\"name\":\"m/s\",\"ratio\":1.0}"
      assertEquals(expectedJson, speedUnitJson)
  }

  @Test
  fun kotlinx_serialize_WhenSpeedIsMeterPerSecond_ProducedJsonIsInMeterPerSecond() {
        val speed = 12.7.meterPerSecond
        val speedJson: String = Json.encodeToString(speed)
        val expectedSpeedJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
        assertEquals(speedJson, expectedSpeedJson)
  }
}