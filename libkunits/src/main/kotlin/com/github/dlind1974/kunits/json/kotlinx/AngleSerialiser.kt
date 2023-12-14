package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Angle
import com.github.dlind1974.kunits.AngleUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json

object AngleSerializer : KSerializer<Angle> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Angle") {
        element<Double>("amount")
        element<String>("unit")
    }

    override fun serialize(encoder: Encoder, value: Angle) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.amount)
            encodeStringElement(descriptor, 1, value.unit.name)
        }
    }

    override fun deserialize(decoder: Decoder): Angle {
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

            val unit = AngleUnit.fromString(unitName)
            Angle(amount, unit)
        }
    }
}

fun Angle.serializeToString(): String {
    return Json.encodeToString(AngleSerializer,this)
}

fun String.deserializeToAngle(): Angle {
    return Json.decodeFromString(AngleSerializer, this)
}
