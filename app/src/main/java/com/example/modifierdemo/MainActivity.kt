package com.example.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                    ModifierDemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ModifierDemoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicBox()
        BoxWithPadding()
        BoxWithBorder()
        OrderDemo()
        ClickableBox()
        ClippedBox()
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

/**
 * Шаг 2. Box с внутренним отступом padding.
 * Демонстрирует Modifier.padding(16.dp) — отодвигает контент от краёв.
 */
@Composable
fun BoxWithPadding(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .background(Color(0xFF03A9F4))
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(text = "padding(16.dp)", color = Color.Black)
    }
}

/**
 * Шаг 3. Box с рамкой (border).
 * Демонстрирует Modifier.border(width, color) и сочетание border + padding + background.
 */
@Composable
fun BoxWithBorder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(140.dp)
            .border(width = 3.dp, color = Color(0xFFE91E63))
            .padding(8.dp)
            .background(Color(0xFFFFC107))
    ) {
        Text(text = "border + padding + background", color = Color.Black)
    }
}

/**
 * Шаг 4. Порядок модификаторов имеет значение.
 * Слева: padding ДО background — фон захватывает только внутреннюю область.
 * Справа: background ДО padding — фон захватывает всё, padding делает контент меньше.
 */
@Composable
fun OrderDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
                .background(Color(0xFF9C27B0))
        ) {
            Text("padding\nbefore\nbackground", color = Color.White)
        }
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color(0xFF9C27B0))
                .padding(16.dp)
        ) {
            Text("background\nbefore\npadding", color = Color.White)
        }
    }
}

/**
 * Шаг 5. Modifier.clickable: реакция на клик с обновлением состояния.
 * Каждый клик увеличивает счётчик; рекомпозиция перерисовывает текст.
 */
@Composable
fun ClickableBox(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Box(
        modifier = modifier
            .size(160.dp, 80.dp)
            .background(Color(0xFF607D8B))
            .clickable { count++ }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Clicked: $count", color = Color.White)
    }
}

/**
 * Шаг 6. Modifier.clip(RoundedCornerShape): обрезка по форме.
 * Важно: clip применяется ПЕРЕД background, иначе фон рисуется поверх формы.
 */
@Composable
fun ClippedBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(160.dp, 80.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFFF5722)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "clip(RoundedCornerShape)", color = Color.White)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ModifierDemoScreenPreview() {
    ModifierDemoTheme {
        ModifierDemoScreen()
    }
}
