package com.example.quadsnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.quadsnap.ui.QuadSnapScreen
import com.example.quadsnap.ui.theme.QuadSnapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadSnapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    QuadSnapScreen()
                }
            }
        }
    }
}
