package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.ui.components.ContactInfo
import com.example.businesscard.ui.components.PersonalInfo
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // Surface - базовый контейнер из Material Design
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

/**
 * Главный Composable-компонент, который собирает весь UI приложения.
 */
@Composable
fun BusinessCardApp() {
    // Основной Column, который занимает весь экран и располагает элементы вертикально.
    Column(
        modifier = Modifier
            .fillMaxSize() // Занимает весь доступный размер
            .background(MaterialTheme.colorScheme.surfaceVariant) // Фоновый цвет
            .padding(16.dp), // Добавим отступы по краям экрана
        horizontalAlignment = Alignment.CenterHorizontally // Все дочерние элементы будут по центру горизонтали
    ) {
        // Первый дочерний элемент - еще одна колонка для верхнего блока.
        // Он будет занимать все доступное место благодаря weight.
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center, // Центрирует PersonalInfo внутри себя по вертикали
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Компонент с личной информацией
            PersonalInfo()
        }

        // Второй дочерний элемент - блок с контактами.
        // Он будет расположен внизу, так как верхний блок занял все остальное место.
        ContactInfo(
            modifier = Modifier.padding(bottom = 32.dp) // Добавим отступ снизу
        )
    }
}

/**
 * Preview для всего экрана приложения.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardAppPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}