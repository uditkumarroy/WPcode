package com.task.repository.local

import androidx.room.Database
import com.task.repository.local.blogs.room.RowCachedEntity

@Database(entities = [RowCachedEntity::class], version = 1)
class TaskDataBase {


    companion object {
        val DATA_BASE: String = "Task_DB"
    }
}
