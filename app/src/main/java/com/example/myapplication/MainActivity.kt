package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterScreen()
        }
    }
}

@Composable
fun CounterScreen() {
    // Состояние: начальное значение 0
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Счётчик",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1F36)
        )

        // Число
        Text(
            text = "$count",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1F36),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Кнопки -1 и +1
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    if (count > 0) count--      // ниже нуля не уходим
                },
                enabled = count > 0,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8ECF4),
                    contentColor = Color(0xFF1A1F36)
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text("−1", fontSize = 18.sp)
            }

            Box(modifier = Modifier.width(12.dp)) // промежуток между кнопками

            Button(
                onClick = {
                    if (count < 10) count++     // выше 10 не поднимаемся
                },
                enabled = count < 10,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F80ED),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text("+1", fontSize = 18.sp)
            }
        }

        // Кнопка "Сбросить"
        Button(
            onClick = { count = 0 },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8ECF4),
                contentColor = Color(0xFF1A1F36)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(52.dp)
        ) {
            Text("Сбросить", fontSize = 16.sp)
        }

        // Сообщение при значении 10
        if (count == 10) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFDDF5E3))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Максимум достигнут",
                    fontSize = 16.sp,
                    color = Color(0xFF1E7B3A)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    CounterScreen()
}