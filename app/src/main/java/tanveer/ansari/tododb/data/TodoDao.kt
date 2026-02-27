package tanveer.ansari.tododb.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo: Todo)
}