package com.github.dlind1974.kunits

class Speed(amount: Double, unit: SpeedUnit) : Quantity<SpeedUnit>(amount, unit)

val Speed.meterPerSecond get() = this.to(SpeedUnit.MeterPerSecond)
val Speed.metersPerSecond get() = this.meterPerSecond
val Speed.mps get() = this.meterPerSecond

val Speed.kilometerPerHour get() = this.to(SpeedUnit.KilometerPerHour)
val Speed.kilometersPerHour get() = this.kilometerPerHour
val Speed.kmph get() = this.kilometerPerHour

val Number.meterPerSecond: Speed get() = Speed(this.toDouble(), SpeedUnit.MeterPerSecond)
val Number.metersPerSecond: Speed get() = this.meterPerSecond
val Number.mps: Speed get() = this.meterPerSecond

val Number.kilometerPerHour: Speed get() = Speed(this.toDouble(), SpeedUnit.KilometerPerHour)
val Number.kilometersPerHour: Speed get() = this.kilometerPerHour
val Number.kmph: Speed get() = this.kilometerPerHour


