package com.glowuptracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.glowuptracker.viewmodel.HomeViewModel
import com.glowuptracker.ui.addhabit.AddHabitScreen
import com.glowuptracker.ui.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(homeViewModel)
        }

        composable("add_habit") {
            AddHabitScreen(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }

        composable("stats") {
            // TODO
        }

        composable("profile") {
            // TODO
        }
    }
}