package app.web.couponrollstudio.model

import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTasksStream(): Flow<List<Task>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getTaskStream(id: String): Flow<Task?>

    /**
     * Insert item in the data source
     */
    suspend fun insertTask(task: Task)

    suspend fun completeTask(task: Task)

    suspend fun activateTask(task: Task)

    suspend fun starTask(task: Task)

    suspend fun unStarTask(task: Task)

    /**
     * Delete task: Task from the data source
     */
    suspend fun deleteTask(task: Task)

    /**
     * Update task: Task in the data source
     */
    suspend fun updateTask(task: Task)
}