package com.task.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.task.repository.local.blogs.room.BlogsDao
import com.task.repository.local.blogs.room.RowCachedEntity

@Database(entities = [RowCachedEntity::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun blogDao(): BlogsDao

    companion object {
        val DATA_BASE_NAME: String = "Task_DB"
    }
}
