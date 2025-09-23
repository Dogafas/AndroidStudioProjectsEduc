package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var rawAmountInput by rememberSaveable { mutableStateOf("") }
    var rawPercentInput by rememberSaveable { mutableStateOf("") }
    var roundUpTip by rememberSaveable { mutableStateOf(false) } // Состояние для Switch

    val amount = parseAndRound(rawAmountInput)
    val tipPercent = parseAndRound(rawPercentInput)
    // Передаем roundUpTip в calculateTipRaw
    val tipAmount = calculateTipRaw(amount, tipPercent, roundUpTip)

    val amountText = stringResource(R.string.tip_amount, formatRublesAndKopecks(amount))
    val tipText = stringResource(R.string.tip_amount, formatRublesAndKopecks(tipAmount))

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start)
        )

        EditNumberField(
            label = stringResource(R.string.bill_amount),
            value = rawAmountInput,
            onValueChange = { rawAmountInput = it },
            maxDigits = 7,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = stringResource(R.string.bill_amount)
                )
            }
        )

        AnimatedVisibility(visible = amount > 0.0) {
            Text(
                text = amountText,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(
            label = stringResource(R.string.tip_percent),
            value = rawPercentInput,
            onValueChange = { rawPercentInput = it },
            maxDigits = 3, // Для процентов обычно достаточно 3 цифр (например, 100)
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.percent),
                    contentDescription = stringResource(R.string.tip_percent)
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = tipText,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(16.dp)) // Небольшой отступ перед Switch

        // Row для Switch и его подписи
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Размещаем элементы по краям
        ) {
            Text(text = stringResource(R.string.round_up_tip))
            Switch(
                checked = roundUpTip,
                onCheckedChange = { roundUpTip = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Небольшой отступ после Switch

        val isPressed = remember { mutableStateOf(false) }
        val buttonColor by animateColorAsState(
            targetValue = if (isPressed.value) Color.Cyan else MaterialTheme.colorScheme.primary
        )

        Button(
            onClick = {
                isPressed.value = true
                rawAmountInput = ""
                rawPercentInput = ""
                roundUpTip = false // Сбрасываем и состояние Switch
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(stringResource(R.string.clear_button_text))
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxDigits: Int, // Максимальное количество цифр в целой части
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = { inputText ->
            val newText = inputText.replace(',', '.')

            if (newText.isEmpty()) {
                onValueChange("")
                return@OutlinedTextField
            }

            var filteredText = ""
            var hasDecimalPoint = false
            for (char in newText) {
                if (char.isDigit()) {
                    filteredText += char
                } else if (char == '.' && !hasDecimalPoint) {
                    filteredText += char
                    hasDecimalPoint = true
                }
            }

            val parts = filteredText.split('.')
            val integerPart = parts[0]
            val decimalPart = if (parts.size > 1) parts[1] else null

            if (integerPart.length <= maxDigits && (decimalPart == null || decimalPart.length <= 2)) {
                 // Если все проверки пройдены, обновляем значение
                onValueChange(filteredText)
            } else if (value.length > filteredText.length && (integerPart.length <= maxDigits && (decimalPart == null || decimalPart.length <=2))) {
                 // Разрешаем удаление символов, если даже после удаления строка остается валидной
                 // или становится валидной по длине.
                 onValueChange(filteredText)
            }
            // В иных случаях (попытка ввести символ, нарушающий правила длины или формата)
            // onValueChange не вызывается, и поле ввода не изменится.
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        trailingIcon = trailingIcon
    )
}

private fun parseAndRound(input: String): Double {
    // Ожидаем, что input уже прошел базовую фильтрацию в EditNumberField
    // и содержит только цифры и, возможно, одну точку.
    val value = input.toDoubleOrNull() ?: 0.0
    // Округляем до 2 знаков после запятой на этапе парсинга
    return String.format("%.2f", value).replace(",", ".").toDouble()
}

// Обновленная функция calculateTipRaw
private fun calculateTipRaw(amount: Double, tipPercent: Double, roundUp: Boolean): Double {
    var rawTip = tipPercent / 100 * amount
    if (roundUp) {
        rawTip = ceil(rawTip) // Округляем вверх, если roundUp = true
    }
    // Форматируем до двух знаков после запятой ПОСЛЕ возможного округления ceil
    return String.format("%.2f", rawTip).replace(",", ".").toDouble()
}

fun formatRublesAndKopecks(amount: Double): String {
    val rubles = amount.toInt()
    // Убедимся, что копейки считаются корректно после ceil
    val kopecks = ((amount - rubles) * 100 + 0.00001).toInt() // Небольшая поправка для точности при работе с Double
    val rubleWord = getRubleWord(rubles)
    val kopeckWord = getKopeckWord(kopecks)
    return "$rubles $rubleWord $kopecks $kopeckWord"
}

fun getRubleWord(number: Int): String {
    val n = number % 100
    return when {
        n in 11..19 -> "рублей"
        n % 10 == 1 -> "рубль"
        n % 10 in 2..4 -> "рубля"
        else -> "рублей"
    }
}

fun getKopeckWord(number: Int): String {
    val n = number % 100
    return when {
        n in 11..19 -> "копеек"
        n % 10 == 1 -> "копейка"
        n % 10 in 2..4 -> "копейки"
        else -> "копеек"
    }
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}
