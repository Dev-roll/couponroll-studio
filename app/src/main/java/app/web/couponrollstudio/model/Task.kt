package app.web.couponrollstudio.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "completed") val isCompleted: Boolean,
    @ColumnInfo(name = "starred") val isStarred: Boolean,
    @ColumnInfo(name = "filepath") val filePath: String
)
