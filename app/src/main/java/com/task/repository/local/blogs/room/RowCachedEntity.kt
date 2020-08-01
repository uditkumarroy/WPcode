package com.task.repository.local.blogs.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blogs")
data class RowCachedEntity(

    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image_href")
    val imageHref: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title: String

)