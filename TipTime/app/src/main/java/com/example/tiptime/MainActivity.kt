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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme

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

    val amount = parseAndRound(rawAmountInput)
    val tipPercent = parseAndRound(rawPercentInput)
    val tipAmount = calculateTipRaw(amount, tipPercent)

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
            maxDigits = 7
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
            maxDigits = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = tipText,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        val isPressed = remember { mutableStateOf(false) }
        val buttonColor by animateColorAsState(
            targetValue = if (isPressed.value) Color.Cyan else MaterialTheme.colorScheme.primary
        )

        Button(
            onClick = {
                isPressed.value = true
                rawAmountInput = ""
                rawPercentInput = ""
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Очистить")
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxDigits: Int,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val cleaned = input.replace(",", ".")
            val regex = "^\\d{0,$maxDigits}(\\.\\d{0,2})?$"
            if (cleaned.matches(Regex(regex))) {
                onValueChange(cleaned)
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier
    )
}

private fun parseAndRound(input: String): Double {
    val cleaned = input.replace(",", ".").replace(Regex("[^\\d.]"), "")
    val value = cleaned.toDoubleOrNull() ?: 0.0
    return String.format("%.2f", value).replace(",", ".").toDouble()
}

private fun calculateTipRaw(amount: Double, tipPercent: Double): Double {
    val rawTip = tipPercent / 100 * amount
    return String.format("%.2f", rawTip).replace(",", ".").toDouble()
}

fun formatRublesAndKopecks(amount: Double): String {
    val rubles = amount.toInt()
    val kopecks = ((amount - rubles) * 100).toInt()
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
