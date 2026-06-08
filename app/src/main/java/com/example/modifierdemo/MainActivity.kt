package com.example.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modifierdemo.ui.theme.ModifierDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifierDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BasicBox(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Шаг 1. Базовый Box с фиксированным размером и цветом фона.
 * Демонстрирует Modifier.size() и Modifier.background().
 */
@Composable
fun BasicBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .background(Color(0xFF4CAF50))
    ) {
        Text(text = "BasicBox", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun BasicBoxPreview() {
    ModifierDemoTheme {
        BasicBox()
    }
}
