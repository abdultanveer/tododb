package tanveer.ansari.tododb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo: Todo)

    @Query("SELECT * from Todo ORDER BY text ASC")
    fun getAllTodos(): List<Todo>

//    suspend fun delete(item: Todo)

}