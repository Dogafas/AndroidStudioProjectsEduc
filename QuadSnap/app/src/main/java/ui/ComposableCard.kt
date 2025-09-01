package com.example.quadsnap.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quadsnap.R
import com.example.quadsnap.ui.theme.QuadSnapTheme


/**
 * Отображает информационную карточку для одного квадранта.
 * ... (KDoc комментарий)
 */
@Composable
fun ComposableCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposableCardPreview() {
    QuadSnapTheme {
        ComposableCard(
            title = stringResource(R.string.composable_text),
            description = stringResource(R.string.material_design_text),
            backgroundColor = Color.LightGray
        )
    }
}