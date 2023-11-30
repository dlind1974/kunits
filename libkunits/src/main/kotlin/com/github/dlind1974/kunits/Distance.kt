package com.github.dlind1974.kunits

class Distance(override val amount: Double, override val unit: DistanceUnit) : Quantity<DistanceUnit>() {
    override fun create(amount: Double, unit: DistanceUnit): Quantity<DistanceUnit> {
        return Distance(amount, unit)
    }
}

val Distance.kilometer get() = this.to(DistanceUnit.Kilometer)
val Distance.kilometers get() = this.kilometer

val Distance.km get() = this.to(DistanceUnit.Kilometer)

val Distance.meter get() = this.to(DistanceUnit.Meter)
val Distance.meters get() = this.meter

val Distance.m get() = this.to(DistanceUnit.Meter)

val Distance.centimeter get() = this.to(DistanceUnit.Centimeter)
val Distance.centimeters get() = this.centimeter
val Distance.cm get() = this.to(DistanceUnit.Centimeter)

val Distance.millimeter get() = this.to(DistanceUnit.Millimeter)
val Distance.millimeters get() = this.millimeter
val Distance.mm get() = this.to(DistanceUnit.Millimeter)

val Number.kilometer: Distance get() = Distance(this.toDouble(), DistanceUnit.Kilometer)
val Number.kilometers: Distance get() = this.kilometer
val Number.km: Distance get() = Distance(this.toDouble(), DistanceUnit.Kilometer)

val Number.meter: Distance get() = Distance(this.toDouble(), DistanceUnit.Meter)
val Number.meters: Distance get() = this.meter

val Number.m: Distance get() = Distance(this.toDouble(), DistanceUnit.Meter)

val Number.centimeter: Distance get() = Distance(this.toDouble(), DistanceUnit.Centimeter)
val Number.centimeters: Distance get() = this.centimeter

val Number.cm: Distance get() = Distance(this.toDouble(), DistanceUnit.Centimeter)

val Number.millimeter: Distance get() = Distance(this.toDouble(), DistanceUnit.Millimeter)
val Number.millimeters: Distance get() = this.millimeter
val Number.mm: Distance get() = Distance(this.toDouble(), DistanceUnit.Millimeter)

