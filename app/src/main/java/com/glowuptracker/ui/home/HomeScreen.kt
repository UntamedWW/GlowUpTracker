package com.glowuptracker.ui.home

import HabitViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    habitViewModel: HabitViewModel
) {
    val habits = habitViewModel.habits

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFB6C1))
    ) {

        // 🔥 Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 25.dp,
                            bottomStart = 25.dp
                        )
                    )
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF511C93),
                                Color(0xFFFF7DFD),
                                Color(0xFFDBFFB6)

                            )
                        )
                    )
                    .padding(24.dp)
            ) {
                Text(
                    "Glow Up Tracker",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Hello, User!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Text(
                    "Ready to become your best self?",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
        item{Spacer(modifier = Modifier.height(24.dp))}
        // 🔥 Fake Tasks
        items(
            listOf(
                "Wake up before 8 AM ☀️",
                "Drink 2L of water 💧",
                "30 min workout 🏋️",
                "Read 10 pages 📖"
            )
        ) { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = Color.LightGray
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(task, fontSize = 16.sp)
                    }

                    Icon(
                        Icons.Filled.Done,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        }

        // 🔥 Real Habits
        items(habits) { habit ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable {
                        habitViewModel.toggleHabit(habit.id)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = habit.name,
                        fontSize = 18.sp
                    )

                    Checkbox(
                        checked = habit.completedToday,
                        onCheckedChange = {
                            habitViewModel.toggleHabit(habit.id)
                        }
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Belive in yourself")
                    Text("You are stronger then you think! \uD83D\uDD25")
                }
            }
        }

        // 🔥 Weekly Progress
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Weekly Progress",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Streak: 0 days")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
