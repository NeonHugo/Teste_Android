package com.nm.infrastructure.util.extensions.format

import com.google.gson.Gson
import org.json.JSONObject
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun localePTBR() = Locale("pt", "BR")

inline fun <reified T> T.toJSONObject(): JSONObject {
    val dataAsString = Gson().toJson(this, T::class.java)
    return JSONObject(dataAsString)
}

fun String.toSafeInt(): Int = try {
    toInt()
} catch (e: Throwable) {
    0
}

fun String.toSafeDouble(): Double = try {
    toDouble()
} catch (e: Throwable) {
    0.0
}

fun String.toSafeBigDecimal(): BigDecimal = try {
    BigDecimal(this.trim())
} catch (e: Throwable) {
    BigDecimal("0")
}

fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(localePTBR())
    numberFormat.minimumFractionDigits = 2

    return numberFormat.format(this).trim()
            .replace("$", "$ ")
            .replace(")", "")
            .replace("(", "-")
}

fun BigDecimal.toCurrencyDollar(): String {
    return this.toCurrency().replace("R", "US")
}

fun BigDecimal.toStringMoney(): String {
    return this.setScale(2).toString()
}

fun BigDecimal.toMoneyNoScale(): String {
    return this.setScale(2).toString().replace(".", "")
}
