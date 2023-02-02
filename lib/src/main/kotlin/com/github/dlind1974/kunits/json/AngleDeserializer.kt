package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.dlind1974.kunits.Angle
import com.github.dlind1974.kunits.AngleUnit
import java.io.IOException

class AngleDeserializer(t: Class<Angle>) : StdDeserializer<Angle>(t) {
    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, deserializer: DeserializationContext): Angle {
        return deserializeQuantity(parser, 0.0, "degree") {
            amount: Double, unit: String -> Angle(amount, AngleUnit.fromString(unit))
        }
    }
}
