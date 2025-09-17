package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf((2..4).random()) }
    when (currentStep) {
        1 -> LemonadeStep(
            textRes = R.string.tap_lemon_tree,
            imageRes = R.drawable.lemon_tree,
            contentDescRes = R.string.lemon_tree_content_description,
        ) {
            squeezeCount = (2..4).random()
            currentStep = 2
        }

        2 -> LemonadeStep(
            textRes = R.string.keep_tapping_lemon,
            imageRes = R.drawable.lemon_squeeze,
            contentDescRes = R.string.lemon_content_description,
        ) {
            squeezeCount--
            if (squeezeCount <= 0) currentStep = 3
        }

        3 -> LemonadeStep(
            textRes = R.string.tap_lemonade,
            imageRes = R.drawable.lemon_drink,
            contentDescRes = R.string.lemonade_content_description,
        ) {
            currentStep = 4
        }

        4 -> LemonadeStep(
            textRes = R.string.tap_empty_glass,
            imageRes = R.drawable.lemon_restart,
            contentDescRes = R.string.empty_glass_content_description,
        ) {
            currentStep = 1
        }
    }
}

@Composable
fun LemonadeStep(
    textRes: Int,
    imageRes: Int,
    contentDescRes: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Кликабельное изображение с фоном
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = stringResource(contentDescRes),
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Основной текст — теперь под изображением
        Text(
            text = stringResource(textRes),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )
    }
}
