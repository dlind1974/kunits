package com.github.dlind1974.kunits.json.kotlinx

import com.github.dlind1974.kunits.Angle
import com.github.dlind1974.kunits.AngleUnit
import kotlinx.serialization.json.Json

object AngleSerializer : QuantitySerializer<Angle>() {
    override fun getTypeName(): String {
        return "Angle"
    }

    override fun getAmount(value: Angle): Double {
        return value.amount
    }

    override fun getUnitName(value: Angle): String {
        return value.unit.name
    }

    override fun createQuantityVariantType(amount: Double, unitName: String): Angle {
        return Angle(amount, AngleUnit.fromString(unitName))
    }
}

fun Angle.serializeToString(): String {
    return Json.encodeToString(AngleSerializer,this)
}

fun String.deserializeToAngle(): Angle {
    return Json.decodeFromString(AngleSerializer, this)
}
