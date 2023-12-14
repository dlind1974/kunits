package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.MeasureUnit
import com.github.dlind1974.kunits.Quantity
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json

abstract class QuantitySerializer<QuantityType> : KSerializer<QuantityType> {

    abstract fun getTypeName() : String
    abstract fun getAmount(value: QuantityType) : Double
    abstract fun getUnitName(value: QuantityType) : String
    abstract fun createQuantityVariantType(amount: Double, unitName: String) : QuantityType

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(getTypeName()) {
        element<Double>("amount")
        element<String>("unit")
    }

    override fun serialize(encoder: Encoder, value: QuantityType) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, getAmount(value))
            encodeStringElement(descriptor, 1, getUnitName(value))
        }
    }

    override fun deserialize(decoder: Decoder) : QuantityType{
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
            createQuantityVariantType(amount, unitName)
        }
    }


}
