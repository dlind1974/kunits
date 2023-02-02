package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.dlind1974.kunits.Speed
import com.github.dlind1974.kunits.SpeedUnit
import java.io.IOException

class SpeedDeserializer(t: Class<Speed>) : StdDeserializer<Speed>(t) {
    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, deserializer: DeserializationContext): Speed {
        return deserializeQuantity(parser, 0.0, SpeedUnit.MeterPerSecond.toString()) {
            amount: Double, unit: String -> Speed(amount, SpeedUnit.fromString(unit))
        }
    }
}
