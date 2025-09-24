package com.example.tiptime

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

// Новый тестовый класс для calculateTipRaw
class TipCalculatorTest {

    @Test
    // Тест: Проверяет, что при нулевом проценте чаевых и без округления сумма чаевых равна нулю.
    fun calculateTipRaw_noRoundUp_zeroTip() {
        val amount = 100.0
        val tipPercent = 0.0
        val expectedTip = 0.0
        val actualTip = calculateTipRaw(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip, 0.001) // Используем дельту для сравнения Double
    }

    @Test
    // Тест: Проверяет расчет 10% чаевых без округления для ровной суммы.
    fun calculateTipRaw_noRoundUp_10PercentTip() {
        val amount = 100.0
        val tipPercent = 10.0
        val expectedTip = 10.0
        val actualTip = calculateTipRaw(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет расчет 15% чаевых без округления для суммы с дробной частью.
    fun calculateTipRaw_noRoundUp_15PercentTip() {
        val amount = 125.50
        val tipPercent = 15.0
        // 125.50 * 0.15 = 18.825 -> форматируется до 18.83
        val expectedTip = 18.83
        val actualTip = calculateTipRaw(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет расчет 10% чаевых с округлением для ровной суммы (округление не должно изменить результат).
    fun calculateTipRaw_roundUp_10PercentTip() {
        val amount = 100.0
        val tipPercent = 10.0
        val expectedTip = 10.0
        val actualTip = calculateTipRaw(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет расчет 15% чаевых с округлением вверх для суммы с дробной частью.
    fun calculateTipRaw_roundUp_15PercentTip() {
        val amount = 125.50
        val tipPercent = 15.0
        // 125.50 * 0.15 = 18.825 -> ceil(18.825) = 19.0 -> форматируется до 19.00
        val expectedTip = 19.00
        val actualTip = calculateTipRaw(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет округление вверх для очень маленькой суммы чаевых (должно округлить до 1.00).
    fun calculateTipRaw_roundUp_smallTip() {
        val amount = 1.0
        val tipPercent = 1.0 // 0.01 -> ceil(0.01) = 1.0
        val expectedTip = 1.00
        val actualTip = calculateTipRaw(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет расчет чаевых без округления при нулевой сумме счета.
    fun calculateTipRaw_zeroAmount() {
        val amount = 0.0
        val tipPercent = 10.0
        val expectedTip = 0.0
        val actualTip = calculateTipRaw(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    // Тест: Проверяет расчет чаевых с округлением при нулевой сумме счета (округление не должно влиять).
    fun calculateTipRaw_roundUp_zeroAmount() {
        val amount = 0.0
        val tipPercent = 10.0
        val expectedTip = 0.0
        val actualTip = calculateTipRaw(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip, 0.001)
    }
}