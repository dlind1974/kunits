package com.github.dlind1974.kunits

class Angle(amount: Double, unit: AngleUnit) : Quantity<AngleUnit>(amount, unit)

val Angle.degrees get() = this.to(AngleUnit.Degrees)
val Angle.degree get() = this.to(AngleUnit.Degree)

val Number.degrees: Angle get() = Angle(this.toDouble(), AngleUnit.Degrees)
val Number.degree: Angle get() = Angle(this.toDouble(), AngleUnit.Degree)
