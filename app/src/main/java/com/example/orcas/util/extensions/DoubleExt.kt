@file:JvmName("DoubleExt")
package com.example.orcas.util.extensions

import java.text.DecimalFormat

@JvmOverloads
fun Double.toFormattedString(format: String = "0.##"): String {
    return DecimalFormat(format).apply {
        this.minimumFractionDigits = 2
    }.format(this)
}