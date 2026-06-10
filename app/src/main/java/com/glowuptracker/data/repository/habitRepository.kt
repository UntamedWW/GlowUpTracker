package com.glowuptracker.data.repository

import com.glowuptracker.model.Habit
import androidx.compose.runtime.mutableStateListOf

class HabitRepository {

    private val habits = mutableStateListOf(
        Habit(
            id = 1,
            title = "Wake up before 8 AM",
            completedToday = false
        ),
        Habit(
            id = 2,
            title = "Drink 2L of water",
            completedToday = false
        ),
        Habit(
            id = 3,
            title = "Workout 30 min",
            completedToday = false
        ),
        Habit(
            id = 4,
            title = "Read 10 pages",
            completedToday = false
        )
    )

    fun getHabits(): List<Habit> {
        return habits
    }

    fun toggleHabit(id: Int) {
        val index = habits.indexOfFirst { it.id == id }

        if (index != -1) {
            habits[index] = habits[index].copy(
                completedToday = !habits[index].completedToday
            )
        }
    }

    fun addHabit(
        name: String,
        reminderTime: String
    ) {
        habits.add(
            Habit(
                id = habits.size + 1,
                title = name,
                reminderTime = reminderTime
            )
        )
    }
}