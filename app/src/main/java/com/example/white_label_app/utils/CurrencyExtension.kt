package com.example.white_label_app.utils

import java.text.DecimalFormat

private val onlyNumberRegex by lazy { "[^0-9 ]".toRegex() }
private val DECIMAL_FACTOR = 100
private val CURRENCY_PATTERN = "R$ #,###,##0.00"

fun String.fromCurrency(): Double = this
    .replace(onlyNumberRegex, "")
    .toDouble()
    .div(DECIMAL_FACTOR)

fun Double.toCurrency(): String = DecimalFormat(CURRENCY_PATTERN)
    .format(this)
