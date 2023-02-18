package app.web.couponrollstudio.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * from tasks WHERE id = :id")
    fun getTask(id: String): Flow<Task>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Task into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)


    @Query("UPDATE tasks SET completed = :completed WHERE id = :id")
    suspend fun updateCompleted(id: String, completed: Boolean)

    @Query("UPDATE tasks SET starred = :starred WHERE id = :id")
    suspend fun updateStarred(id: String, starred: Boolean)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}