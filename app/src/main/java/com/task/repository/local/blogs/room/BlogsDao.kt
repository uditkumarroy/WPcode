package com.task.repository.local.blogs.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rowCachedEntity: RowCachedEntity): Long

    @Query("SELECT * from Blogs")
    suspend fun getBlogs(): List<RowCachedEntity>

}