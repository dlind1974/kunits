package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.dlind1974.kunits.Area
import java.io.IOException

class AreaSerializer(t: Class<Area>) : StdSerializer<Area>(t) {
    @Throws(IOException::class)
    override fun serialize(
        speed: Area, jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        serializeQuantity(jsonGenerator, speed)
    }
}