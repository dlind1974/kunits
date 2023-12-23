package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Area
import com.github.dlind1974.kunits.AreaUnit
import kotlinx.serialization.json.Json

object AreaSerializer : QuantitySerializer<Area>() {
    override fun getTypeName(): String {
        return "Area"
    }

    override fun getAmount(value: Area): Double {
        return value.amount
    }

    override fun getUnitName(value: Area): String {
        return value.unit.name
    }

    override fun createQuantityVariantType(amount: Double, unitName: String): Area {
        return Area(amount, AreaUnit.fromString(unitName))
    }
}

fun Area.serializeToString(): String {
    return Json.encodeToString(AreaSerializer,this)
}

fun String.deserializeToArea(): Area {
    return Json.decodeFromString(AreaSerializer, this)
}
