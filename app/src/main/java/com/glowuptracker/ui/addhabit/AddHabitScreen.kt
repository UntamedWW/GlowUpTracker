package com.glowuptracker.ui.addhabit

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.glowuptracker.viewmodel.HomeViewModel


@Composable
fun AddHabitScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFB6C1))
        ) {
        //header
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Add New Habit",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Column(
            modifier = Modifier
                .padding(20.dp, 100.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var habitName by remember { mutableStateOf("") }

            OutlinedTextField(
                value = habitName,
                onValueChange = { habitName = it },
                label = { Text("Habit") },
                modifier = Modifier.fillMaxWidth()
            )

            var time by remember { mutableStateOf("") }
            var showPicker by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = time,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(
                    "Set a reminder",
                    modifier = Modifier.clickable { showPicker = true }) },
                trailingIcon = {
                    Text(
                        "🕒",
                        modifier = Modifier
                            .clickable { showPicker = true }
                            .size(30.dp)
                    )
                }
            )

            if(showPicker) {
                HabitTimePicker(
                    onConfirm = { hour, minute ->
                        time = String.format("%02d:%02d", hour, minute)
                        showPicker = false
                    },
                    onDismiss = {
                        showPicker = false
                    }
                )
            }

            Button(
                onClick = {

                    homeViewModel.addHabit(
                        title = habitName,
                        reminderTime = time
                    )

                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitTimePicker(
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit
){
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        timePickerState.hour,
                        timePickerState.minute
                    )
                }
            ){
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            TimePicker(state = timePickerState)
        }
    )
}
