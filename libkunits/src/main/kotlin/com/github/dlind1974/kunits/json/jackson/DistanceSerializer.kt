package com.github.dlind1974.kunits.json.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.dlind1974.kunits.Distance
import java.io.IOException

class DistanceSerializer(t: Class<Distance>) : StdSerializer<Distance>(t) {
    @Throws(IOException::class)
    override fun serialize(
        distance: Distance, jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        serializeQuantity(jsonGenerator, distance)
    }
}