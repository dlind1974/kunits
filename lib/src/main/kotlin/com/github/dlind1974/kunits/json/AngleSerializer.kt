package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.dlind1974.kunits.Angle
import java.io.IOException

class AngleSerializer(t: Class<Angle>) : StdSerializer<Angle>(t) {
    @Throws(IOException::class)
    override fun serialize(
        angle: Angle, jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        serializeQuantity(jsonGenerator, angle)
    }
}
