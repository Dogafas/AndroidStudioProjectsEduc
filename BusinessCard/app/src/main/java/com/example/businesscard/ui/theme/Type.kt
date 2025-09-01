package com.example.businesscard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.businesscard.R
import androidx.compose.ui.text.font.Font


val OrbitronFamily = FontFamily(
    Font(R.font.orbitron_regular, FontWeight.Normal),     // regular -> Normal (W400)
    Font(R.font.orbitron_medium, FontWeight.Medium),       // medium -> Medium (W500)
    Font(R.font.orbitron_semibold, FontWeight.SemiBold),   // semibold -> SemiBold (W600)
    Font(R.font.orbitron_bold, FontWeight.Bold),           // bold -> Bold (W700)
    Font(R.font.orbitron_extrabold, FontWeight.ExtraBold), // extrabold -> ExtraBold (W800)
    Font(R.font.orbitron_black, FontWeight.Black)          // black -> Black (W900 - самый жирный)
)

val Typography = Typography(
    // Стиль для основного текста
    bodyLarge = TextStyle(
        fontFamily = OrbitronFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Стиль для больших заголовков (наше имя)
    headlineLarge = TextStyle(
        fontFamily = OrbitronFamily,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp
    ),
    // Стиль для подзаголовка (должности)
    titleMedium = TextStyle(
        fontFamily = OrbitronFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
)