package com.sofiia.glowuptracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FakeHabit(
    val id: Int,
    val name: String,
    val icon: String,
    var completed: Boolean
)

@Composable
fun HomeScreen(
    onAddClick: () -> Unit = {}
) {

    val habits = remember {
        mutableStateListOf(
            FakeHabit(1, "Workout", "🏋️", false),
            FakeHabit(2, "Drink Water", "💧", false),
            FakeHabit(3, "Read Book", "📖", false),
            FakeHabit(4, "Code Practice", "💻", false)
        )
    }

    val streak = if (habits.all { it.completed }) 5 else 0
    val weeklyProgress = habits.count { it.completed } / habits.size.toFloat()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Habit")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFEDE7F6),
                            Color(0xFFD1C4E9)
                        )
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Column {
                    Text(
                        text = "Hello, User ✨",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ready to glow today?",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }
            }

            item {
                Card(
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Believe in yourself.",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Small daily actions build unstoppable discipline."
                        )
                    }
                }
            }

            items(habits) { habit ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            habit.completed = !habit.completed
                        },
                    shape = MaterialTheme.shapes.large
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = habit.icon,
                                fontSize = 22.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = habit.name,
                                fontSize = 18.sp
                            )
                        }

                        Checkbox(
                            checked = habit.completed,
                            onCheckedChange = {
                                habit.completed = it
                            }
                        )
                    }
                }
            }

            item {
                Card(
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Weekly Progress",
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LinearProgressIndicator(
                            progress = weeklyProgress,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Streak: $streak days"
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}
