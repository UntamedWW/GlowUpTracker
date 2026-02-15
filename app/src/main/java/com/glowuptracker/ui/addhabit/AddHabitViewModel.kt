package com.sofiia.glowuptracker.ui.addhabit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddHabitViewModel : ViewModel() {

    var habitName by mutableStateOf("")
        private set

    var selectedDays by mutableStateOf(setOf<String>())
        private set

    private val allDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    fun onNameChange(newName: String) {
        habitName = newName
    }

    fun onDayToggle(day: String) {
        selectedDays = if (selectedDays.contains(day)) {
            selectedDays - day
        } else {
            selectedDays + day
        }
    }

    fun getAllDays(): List<String> = allDays

    fun canSave(): Boolean {
        return habitName.isNotBlank() && selectedDays.isNotEmpty()
    }

    fun saveHabit() {
        // Later this will save to Room
        println("Habit Saved: $habitName, Days: $selectedDays")
    }
}
