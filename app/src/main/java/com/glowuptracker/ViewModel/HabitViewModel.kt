import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Habit(
    val id: Int,
    val name: String,
    val icon: String,
    var completedToday: Boolean = false
)

 class HabitViewModel : ViewModel() {
    var habits = mutableStateListOf<Habit>()
        private set

     fun addHabit(){

     }

     fun toggleHabit(id: Int) {

     }
}