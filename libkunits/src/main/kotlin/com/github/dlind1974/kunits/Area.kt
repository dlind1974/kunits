package com.github.dlind1974.kunits

class Area(override val amount: Double, override val unit: AreaUnit) : Quantity<AreaUnit>() {
    override fun create(amount: Double, unit: AreaUnit): Quantity<AreaUnit> {
        return Area(amount, unit)
    }
}

val Area.squareMeter get() = this.to(AreaUnit.SquareMeter)
val Area.squareMeters get() = this.squareMeter
val Area.m2 get() = this.to(AreaUnit.SquareMeter)

val Area.squareCentimeter get() = this.to(AreaUnit.SquareCentimeter)
val Area.squareCentimeters get() = this.squareCentimeter
val Area.cm2 get() = this.to(AreaUnit.SquareCentimeter)

val Number.squareMeter: Area get() = Area(this.toDouble(), AreaUnit.SquareMeter)
val Number.squareMeters: Area get() = this.squareMeter
val Number.m2: Area get() = Area(this.toDouble(), AreaUnit.SquareMeter)

val Number.squareCentimeter: Area get() = Area(this.toDouble(), AreaUnit.SquareCentimeter)
val Number.squareCentimeters: Area get() = this.squareCentimeter
val Number.cm2: Area get() = Area(this.toDouble(), AreaUnit.SquareCentimeter)
