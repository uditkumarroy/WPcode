package com.task.repository.local.blogs.room

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blogs")
class RowCachedEntity(

    @ColumnInfo(name = "description")
    @Nullable
    var description: String,
    @ColumnInfo(name = "image_href")
    @Nullable
    var imageHref: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    var title: String

)