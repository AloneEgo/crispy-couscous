package ru.netology

import org.junit.Assert.*
import org.junit.Test

class MainTest {

    @Test
    fun commissionCalculateEqualDayLimitAndMirNoCommission() {

        //Проверка на равенство дневного лимита и отсутствие комиссии за карту Мир
        val cardType = "Мир"
        val sum = 0
        val transfer = 150_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод равен дневному лимиту и карта Мир", 0, result)
    }

    @Test
    fun commissionCalculateExceedDayLimit()   {
        //Проверка на превышение дневного лимита
        val cardType = "Мир"
        val sum = 0
        val transfer = 200_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод больше дневного лимита",-1, result)
    }

    @Test
    fun commissionCalculateEqualMonthLimitAndVisaMinimalCommission() {
        //Проверка на равенство месячному лимиту и минимальную комиссию Visd
        val cardType = "Visa"
        val sum = 599_999
        val transfer = 1
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод равен месячному лимиту и карта Visa с минимальной комиссией",
            35, result)
    }

    @Test
    fun commissionCalculateExceedMonthLimit() {
        //Проверка на превышение месячного лимита
        val cardType = "Visa"
        val sum = 600_000
        val transfer = 1
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод больше месячного лимита",-1, result)
    }

    @Test
    fun commissionCalculateNegativeTransfer() {
        //Проверка на защиту от отрицательного перевода
        val cardType = "Visa"
        val sum = 0
        val transfer = -1
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод меньше нуля",-1, result)
    }

    @Test
    fun commissionCalculateVisaCommission() {
        //Проверка на комиссию Visa
        val cardType = "Visa"
        val sum = 0
        val transfer = 4800
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод Visa c комиссией",36, result)
    }

    @Test
    fun commissionCalculateMastercardNoCommission() {
        //Льготный порог не превышен
        val cardType = "Mastercard"
        val sum = 35_000
        val transfer = 35_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод Mastercard до льготного порога за месяц",0, result)
    }

    @Test
    fun commissionCalculateMastercardCurrentCommission() {
        //льготный порог превышен ранее (комиссия взимается только с текущего платежа)
        val cardType = "Mastercard"
        val sum = 75_000
        val transfer = 100_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод Mastercard до льготного порога за месяц",620, result)
    }

    @Test
    fun commissionCalculateMastercardDiffCommission() {
        //льготный порог превышается текущим платежом (комиссия взимается с суммы превышения)
        val cardType = "Mastercard"
        val sum = 35_000
        val transfer = 140_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Перевод Mastercard до льготного порога за месяц",620, result)
    }

    @Test
    fun commissionCalculateWrongCardType() {
        //ошибка в названии карты
        val cardType = "Maestro"
        val sum = 35_000
        val transfer = 140_000
        val result = commissionCalculate(cardType, sum, transfer)
        assertEquals("Несуществующий тип карты",-1, result)
    }


}