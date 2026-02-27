package tanveer.ansari.tododb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import tanveer.ansari.tododb.data.Todo
import tanveer.ansari.tododb.data.TodoDao
import tanveer.ansari.tododb.data.TodoDatabase
import tanveer.ansari.tododb.ui.theme.TododbTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_db"
        ).build()

        setContent {
            TododbTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoScreen(db.todoDao())
                }
            }
        }
    }
}

@Composable
fun TodoScreen(dao: TodoDao) {
    var todos by remember { mutableStateOf(listOf<Todo>())}

    var text by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column {

        TextField(
            value = text,
            onValueChange = { text = it }
        )

        Button(
            onClick = {
                scope.launch {
                    dao.insert(Todo(text = text))
                }
            }
        ) {
            Text("Save Todo")
        }

        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                   todos = dao.getAllTodos()
                    Log.i("Mainactivity","todo-"+todos.toString())
                }
            }
        ) {
            Text("get Todos")
        }
        Text(text = todos.toString())

        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                   // dao.delete()
                }
            }
        ) {
            Text("delete Todos")
        }
    }
}

