package com.github.dlind1974.kunits

fun Double.format(decimals: UInt) = "%.${decimals}f".format(this)
