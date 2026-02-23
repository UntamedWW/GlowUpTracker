package com.glowuptracker

import HabitViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.glowuptracker.ui.addhabit.AddHabitScreen
import com.glowuptracker.ui.home.HomeScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val habitViewModel: HabitViewModel = viewModel()

            Scaffold(
                bottomBar = {
                    BottomBar(navController)
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("add_habit")
                        }
                    ) {
                        Icon(Icons.Sharp.Edit, contentDescription = null)
                    }
                }
            ) { padding ->

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(padding)
                ) {

                    composable("home") {
                        HomeScreen(habitViewModel)
                    }

                    composable("add_habit") {
                        AddHabitScreen(habitViewModel, navController)
                    }

                    composable("stats") {
                        Text("Stats Screen")
                    }

                    composable("profile") {
                        Text("Profile Screen")
                    }
                }
            }
        }
    }
}


@Composable
fun BottomBar(
    navController: NavController
) {

    BottomAppBar(
        containerColor = Color(0xFF8C54CE)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = false }
                    launchSingleTop = true
                }
            }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    Modifier.size(30.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate("add_habit")
            }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    Modifier.size(30.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate("stats")
            }) {
                Icon(
                    Icons.Rounded.CheckCircle,
                    contentDescription = "Stats",
                    Modifier.size(30.dp)
                )
            }

            IconButton(onClick = {
                navController.navigate("profile")
            }) {
                Icon(
                    Icons.Sharp.AccountCircle,
                    contentDescription = "Profile",
                    Modifier.size(30.dp)
                )
            }
        }
    }
}