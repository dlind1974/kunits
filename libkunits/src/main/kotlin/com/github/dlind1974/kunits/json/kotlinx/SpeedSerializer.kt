package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Speed
import com.github.dlind1974.kunits.SpeedUnit
import kotlinx.serialization.json.Json

object SpeedSerializer : QuantitySerializer<Speed>() {
    override fun getTypeName(): String {
        return "Speed"
    }

    override fun getAmount(value: Speed): Double {
        return value.amount
    }

    override fun getUnitName(value: Speed): String {
        return value.unit.name
    }

    override fun createQuantityVariantType(amount: Double, unitName: String): Speed {
        return Speed(amount, SpeedUnit.fromString(unitName))
    }
}

fun Speed.serializeToString(): String {
    return Json.encodeToString(SpeedSerializer,this)
}

fun String.deserializeToSpeed(): Speed {
    return Json.decodeFromString(SpeedSerializer, this)
}
