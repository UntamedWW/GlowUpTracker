package com.glowuptracker.model

data class Habit(
    val id: Int,
    val title: String,
    val streak: Int = 0,
    val reminderTime: String = "",
    val completedToday: Boolean = false
)