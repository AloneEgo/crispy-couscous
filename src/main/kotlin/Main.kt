package ru.netology

import kotlin.math.max

fun main() {

    println(commissionCalculate("Visa", 0, 100_000)) //проверка

}

fun commissionCalculate(cardType: String = "Мир", sum: Int = 0, transfer: Int): Int {

    //Максимальная сумма перевода с одной карты:
    val maxDay = 150_000    //150 000 руб. в сутки
    val maxMonth = 600_000  //600 000 руб. в месяц

    // За переводы с карты Мир комиссия не взимается.

    // За переводы с карты Mastercard комиссия не взимается, пока не превышен месячный лимит в 75 000 руб.
    // Если лимит превышен, комиссия составит 0,6% + 20 руб.
    val masterCardMinLimit = 75_000 //минимальная сумма mastercard без комиссии
    val masterCardCommission = 60 //комиссия mastercard в сотых процента
    val masterCardPlusCommission = 20 //добавление комиссии mastercard

    // За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
    val visaCommission = 75 //комиссия visa в сотых процента
    val visaMinCommission = 35 //минимальная комиссия visa

    val fail = -1 //значение ошибки операции

    if (transfer > maxDay || sum + transfer > maxMonth) {
        return fail
    }

    return when (cardType) {
        "Мир" -> 0
        "Visa" -> max(visaMinCommission, transfer * visaCommission / 10_000)
        "Mastercard" -> if (transfer < masterCardMinLimit) {
            0
        } else {
            (transfer - masterCardMinLimit) * masterCardCommission / 10_000 + masterCardPlusCommission
        }

        else -> fail
    }

}