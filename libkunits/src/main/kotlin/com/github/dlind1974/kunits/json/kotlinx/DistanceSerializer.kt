package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Distance
import com.github.dlind1974.kunits.DistanceUnit
import kotlinx.serialization.json.Json

object DistanceSerializer : QuantitySerializer<Distance>() {
    override fun getTypeName(): String {
        return "Distance"
    }

    override fun getAmount(value: Distance): Double {
        return value.amount
    }

    override fun getUnitName(value: Distance): String {
        return value.unit.name
    }

    override fun createQuantityVariantType(amount: Double, unitName: String): Distance {
        return Distance(amount, DistanceUnit.fromString(unitName))
    }
}

fun Distance.serializeToString(): String {
    return Json.encodeToString(DistanceSerializer,this)
}

fun String.deserializeToDistance(): Distance {
    return Json.decodeFromString(DistanceSerializer, this)
}
