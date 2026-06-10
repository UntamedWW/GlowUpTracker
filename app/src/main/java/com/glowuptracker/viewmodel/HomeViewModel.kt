package com.glowuptracker.viewmodel

import androidx.lifecycle.ViewModel
import com.glowuptracker.data.repository.HabitRepository

class HomeViewModel : ViewModel() {

    private val repository = HabitRepository()

    val habits
        get() = repository.getHabits()

    fun toggleHabit(id: Int) {
        repository.toggleHabit(id)
    }

    fun addHabit(
        title: String,
        reminderTime: String
    ) {

        if (title.isBlank()) return

        repository.addHabit(
            title,
            reminderTime
        )
    }
}