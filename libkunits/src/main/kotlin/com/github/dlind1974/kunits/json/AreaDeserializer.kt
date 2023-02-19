package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.dlind1974.kunits.Area
import com.github.dlind1974.kunits.AreaUnit
import java.io.IOException

class AreaDeserializer(t: Class<Area>) : StdDeserializer<Area>(t) {
    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, deserializer: DeserializationContext): Area {
        return deserializeQuantity(parser, 0.0, AreaUnit.SquareMeter.toString()) {
                amount: Double, unit: String -> Area(amount, AreaUnit.fromString(unit))
        }
    }
}