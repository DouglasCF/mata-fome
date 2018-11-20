package br.com.fornaro.matafome.utils

import java.text.NumberFormat
import java.util.*

object MoneyUtils {

    fun formatMoney(value: Float): String {
        val ptBr = Locale("pt", "BR")
        return NumberFormat.getCurrencyInstance(ptBr).format(value)
    }
}
