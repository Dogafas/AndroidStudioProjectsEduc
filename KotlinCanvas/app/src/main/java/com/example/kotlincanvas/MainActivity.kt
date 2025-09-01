package com.example.kotlincanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlincanvas.ui.theme.KotlinCanvasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCanvasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Вызываем нашу новую функцию, чтобы она стала главным экраном
                    ComposeArticleScreen()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.verticalScroll(state = scrollState)) {
        // 1. Изображение
        val image = painterResource(id = R.drawable.bg_compose_background)
        Image(
            painter = image,
            contentDescription = stringResource(R.string.jetpack_compose_background),
            modifier = Modifier.fillMaxWidth() // Растягиваем на всю ширину
        )

        // 2. Первый текстовый композит (заголовок)
        Text(
            text = stringResource(R.string.jetpack_compose_tutorial),
            fontSize = 24.sp, // Размер шрифта 24sp
            modifier = Modifier.padding(16.dp) // Отступ 16dp со всех сторон
        )

        // 3. Второй текстовый композит
        Text(
            text = stringResource(R.string.description_1),
            textAlign = TextAlign.Justify, // Выравнивание по ширине
            modifier = Modifier.padding(start = 16.dp, end = 16.dp) // Отступы 16dp по бокам
        )

        // 4. Третий текстовый композит
        Text(
            text = stringResource(R.string.description_2),
            textAlign = TextAlign.Justify, // Выравнивание по ширине
            modifier = Modifier.padding(16.dp) // Отступ 16dp со всех сторон
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeArticlePreview() {
    KotlinCanvasTheme {
        ComposeArticleScreen()
    }
}