package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.dlind1974.kunits.Speed
import java.io.IOException

class SpeedSerializer(t: Class<Speed>) : StdSerializer<Speed>(t) {
    @Throws(IOException::class)
    override fun serialize(
        speed: Speed, jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        serializeQuantity(jsonGenerator, speed)
    }
}
