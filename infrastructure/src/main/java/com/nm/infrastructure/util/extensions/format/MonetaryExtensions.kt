package com.nm.infrastructure.util.extensions.format

import android.icu.lang.UProperty.DASH
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

fun BigDecimal?.toCurrency() = this?.run {
    if (this > BigDecimal.ZERO)
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
    else
        DASH
} ?: DASH

fun Double?.toCurrency() = this?.run {
    if (this > 0) {
        val fmt =
            NumberFormat.getInstance() as DecimalFormat
        val locale = Locale.getDefault()
        val symbol = Currency.getInstance(locale).getSymbol(locale)
        fmt.isGroupingUsed = true
        fmt.positivePrefix = "$symbol "
        fmt.negativePrefix = "-$symbol "
        fmt.minimumFractionDigits = 2
        fmt.maximumFractionDigits = 2
        fmt.format(this)
    } else
        DASH
} ?: DASH

fun Double?.toPercentage() = this?.run {
    if (this > 0) {
        val fmt =
            NumberFormat.getInstance() as DecimalFormat
        val locale = Locale.getDefault()
        val symbol = Currency.getInstance(locale).getSymbol(locale)
        fmt.isGroupingUsed = true
        fmt.positivePrefix = ""
        fmt.negativePrefix = ""
        fmt.minimumFractionDigits = 2
        fmt.maximumFractionDigits = 2
        fmt.format(this) + " %"
    } else
        DASH
} ?: DASH

fun String?.toCurrency(): BigDecimal {
    val dfs = DecimalFormatSymbols(Locale("pt", "BR"))
    val df = DecimalFormat("#,##0.00", dfs)
    val bigDecimal = BigDecimal(df.parse(this).toString())
    return bigDecimal.setScale(2, RoundingMode.HALF_UP)
}