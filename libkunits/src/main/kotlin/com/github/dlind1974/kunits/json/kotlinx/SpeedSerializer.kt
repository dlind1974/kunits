package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Speed
import com.github.dlind1974.kunits.SpeedUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json

object SpeedSerializer : KSerializer<Speed> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Speed") {
        element<Double>("amount")
        element<String>("unit")
    }

    override fun serialize(encoder: Encoder, value: Speed) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.amount)
            encodeStringElement(descriptor, 1, value.unit.name)
        }
    }

    override fun deserialize(decoder: Decoder): Speed {
        return decoder.decodeStructure(descriptor) {
            var amount = 0.0
            var unitName = ""

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    CompositeDecoder.DECODE_DONE -> break
                    0 -> amount = decodeDoubleElement(descriptor, index)
                    1 -> unitName = decodeStringElement(descriptor, index)
                    else -> throw SerializationException("Unknown index $index")
                }
            }

            val unit = SpeedUnit.fromString(unitName)
            Speed(amount, unit)
        }
    }
}

fun Speed.serializeToString(): String {
    return Json.encodeToString(SpeedSerializer,this)
}

fun String.deserializeToSpeed(): Speed {
    return Json.decodeFromString(SpeedSerializer, this)
}
