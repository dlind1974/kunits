package com.github.dlind1974.kunits.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.github.dlind1974.kunits.MeasureUnit
import com.github.dlind1974.kunits.Quantity

fun <T> deserializeQuantity(
    parser: JsonParser,
    defaultAmount: Double,
    defaultUnit: String,
    createQuantity: (amount: Double, unitString: String) -> T
): T {
    var amount: Double = defaultAmount
    var unitString: String = defaultUnit

    while (!parser.isClosed) {
        val jsonToken = parser.nextToken()

        if (JsonToken.END_OBJECT == jsonToken) {
            break
        }

        if (JsonToken.FIELD_NAME == jsonToken) {
            val fieldName = parser.currentName
            parser.nextToken()
            if ("amount" == fieldName) {
                amount = parser.doubleValue
            } else if ("unit" == fieldName) {
                unitString = parser.valueAsString
            }
        }
    }

    return createQuantity(amount, unitString)
}

fun <T : MeasureUnit> serializeQuantity(
    jsonGenerator: JsonGenerator, quantity: Quantity<T>
) {
    jsonGenerator.writeStartObject()
    jsonGenerator.writeNumberField("amount", quantity.amount)
    jsonGenerator.writeStringField("unit", quantity.unit.name)
    jsonGenerator.writeEndObject()
}

