package com.task.repository.local.blogs.room

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blogs")
data class RowCachedEntity(

    @ColumnInfo(name = "description")
    @Nullable
    val description: String,
    @ColumnInfo(name = "image_href")
    @Nullable
    val imageHref: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title: String

)