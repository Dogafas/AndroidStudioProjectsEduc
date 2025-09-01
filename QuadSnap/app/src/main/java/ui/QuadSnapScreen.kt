package com.example.quadsnap.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quadsnap.R
import com.example.quadsnap.ui.theme.QuadSnapTheme

/**
 * Основной экран приложения, отображающий сетку 2x2 из информационных карточек.
 */
@Composable
fun QuadSnapScreen() {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            ComposableCard(
                title = stringResource(R.string.composable_text),
                description = stringResource(R.string.material_design_text),
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposableCard(
                title = stringResource(R.string.composabe_image),
                description = stringResource(R.string.material_design_painter),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ComposableCard(
                title = stringResource(R.string.compose_string),
                description = stringResource(R.string.composable_design_striing),
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposableCard(
                title = stringResource(R.string.compose_colomn),
                description = stringResource(R.string.composable_design_colomn),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuadSnapScreenPreview() {
    QuadSnapTheme {
        QuadSnapScreen()
    }
}