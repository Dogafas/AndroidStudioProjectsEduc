package com.example.composecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.ui.theme.ComposeCraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeCraftTheme {
                // Используем Surface как корневой элемент нашего UI
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Вызываем нашу новую Composable-функцию
                    TaskCompletedScreen()
                }
            }
        }
    }
}

/**
 * Отображает экран с сообщением о завершении всех задач.
 *
 * @param modifier Модификатор для настройки внешнего вида и поведения этого Composable.
 */
@Composable
fun TaskCompletedScreen(modifier: Modifier = Modifier) {
    // Column располагает элементы вертикально и центрирует их
    Column(
        modifier = modifier.fillMaxSize(), // Занимаем весь экран
        verticalArrangement = Arrangement.Center, // Центрируем по вертикали
        horizontalAlignment = Alignment.CenterHorizontally // Центрируем по горизонтали
    ) {
        // Загружаем изображение из ресурсов drawable
        val image = painterResource(id = R.drawable.ic_task_completed)
        Image(
            painter = image,
            contentDescription = null // Изображение чисто декоративное
        )

        // Первый текст: "All tasks completed"
        Text(
            text = stringResource(R.string.all_tasks_completed),
            fontWeight = FontWeight.Bold, // Жирный шрифт
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp) // Отступы
        )

        // Второй текст: "Nice work!"
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp // Размер шрифта
        )
    }
}

/**
 * Preview-функция для нашего нового экрана.
 * Позволяет видеть результат в окне предпросмотра Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskCompletedPreview() {
    ComposeCraftTheme {
        TaskCompletedScreen()
    }
}