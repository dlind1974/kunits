package com.github.dlind1974.kunits.json.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.dlind1974.kunits.Distance
import com.github.dlind1974.kunits.DistanceUnit
import java.io.IOException

class DistanceDeserializer(t: Class<Distance>) : StdDeserializer<Distance>(t) {
    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, deserializer: DeserializationContext): Distance {
        return deserializeQuantity(parser, 0.0, DistanceUnit.Meter.toString()) {
                amount: Double, unit: String -> Distance(amount, DistanceUnit.fromString(unit))
        }
    }
}

