package com.example.businesscard.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.R
import com.example.businesscard.ui.theme.BusinessCardTheme

/**
 * Отображает одну строку контактной информации с иконкой и текстом.
 * Это маленький, переиспользуемый компонент.
 *
 * @param iconResId ID ресурса drawable для иконки.
 * @param text Текст для отображения.
 * @param modifier Модификатор для настройки внешнего вида.
 */
@Composable
fun ContactRow(
    @DrawableRes iconResId: Int, // Аннотация для безопасности, говорит что сюда нужен ID ресурса
    text: String,
    modifier: Modifier = Modifier
) {
    // Row располагает элементы горизонтально, друг за другом
    Row(
        // Выравниваем иконку и текст по центру вертикали
        verticalAlignment = Alignment.CenterVertically,
        // Добавляем внешние отступы для всей строки
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        // Иконка
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null, // Текст рядом уже описывает суть, поэтому здесь можно null
            modifier = Modifier.size(24.dp)
        )

        // Горизонтальный отступ между иконкой и текстом
        Spacer(modifier = Modifier.width(16.dp))

        // Текст контакта
        Text(text = text)
    }
}


/**
 * Отображает весь блок с контактной информацией.
 * @param modifier Модификатор для настройки внешнего вида.
 */
@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    // Column располагает все строки контактов вертикально
    Column(modifier = modifier) {
        // Вызываем компонент ContactRow три раза с разными данными
        ContactRow(
            iconResId = R.drawable.phone_icon,
            text = "+7-(924)-706-56-46"
        )
        ContactRow(
            iconResId = R.drawable.telegram_logo,
            text = "@GallacticGazelle"
        )
        ContactRow(
            iconResId = R.drawable.inbox_message_email,
            text = "solomindavid10@gmail.com"
        )
    }
}


/**
 * Preview-функция для отображения компонента ContactInfo в окне предпросмотра.
 */
@Preview(showBackground = true)
@Composable
fun ContactInfoPreview() {
    BusinessCardTheme {
        ContactInfo()
    }
}