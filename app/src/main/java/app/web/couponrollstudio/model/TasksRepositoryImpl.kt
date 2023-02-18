package app.web.couponrollstudio.model

import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val taskDao: TaskDao) : TasksRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()

    override fun getTaskStream(id: String): Flow<Task?> = taskDao.getTask(id)

    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun completeTask(task: Task) = taskDao.updateCompleted(task.id, true)

    override suspend fun activateTask(task: Task) = taskDao.updateCompleted(task.id, false)

    override suspend fun starTask(task: Task) = taskDao.updateStarred(task.id, true)

    override suspend fun unStarTask(task: Task) = taskDao.updateStarred(task.id, false)

    override suspend fun deleteTask(task: Task) = taskDao.delete(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)
}