package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Инструментальные тесты для проверки UI приложения TipTime.
 */
@RunWith(AndroidJUnit4::class)
class TipTimeUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Тест: Проверяет, что при вводе суммы счета и процента чаевых,
     * корректно отображается рассчитанная сумма чаевых без округления.
     */
    @Test
    fun calculate_20_percent_tip_no_round_up() {
        // Устанавливаем содержимое Compose для тестирования
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        // Вводим сумму счета
        composeTestRule.onNodeWithText("Сумма счёта")
            .performTextInput("100")

        // Вводим процент чаевых
        composeTestRule.onNodeWithText("Процент чаевых")
            .performTextInput("20")

        // Проверяем, что сумма чаевых отображается корректно
        composeTestRule.onNodeWithText("Сумма чаевых: 20 рублей 0 копеек")
            .assertExists()
    }
}