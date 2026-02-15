package com.sofiia.glowuptracker.ui.addhabit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(
    viewModel: AddHabitViewModel,
    onSaveClick: () -> Unit = {}
) {

    val habitName = viewModel.habitName
    val selectedDays = viewModel.selectedDays
    val allDays = viewModel.getAllDays()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Habit") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Habit Name
            OutlinedTextField(
                value = habitName,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Habit Name") },
                modifier = Modifier.fillMaxWidth()
            )

            // Days Selection
            Text(
                text = "Select Days",
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                allDays.forEach { day ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = selectedDays.contains(day),
                                onValueChange = { viewModel.onDayToggle(day) }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = selectedDays.contains(day),
                            onCheckedChange = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = day)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.saveHabit()
                    onSaveClick()
                },
                enabled = viewModel.canSave(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Habit")
            }
        }
    }
}
