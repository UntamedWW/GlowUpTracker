import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.glowuptracker.ui.addhabit.AddHabitScreen
import com.glowuptracker.ui.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    habitViewModel: HabitViewModel
) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                habitViewModel = habitViewModel
            )
        }

        composable("add_habit") {
            AddHabitScreen(
                navController = navController,
                habitViewModel = habitViewModel
            )
        }
    }
}
