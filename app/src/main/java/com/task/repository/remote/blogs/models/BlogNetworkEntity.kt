package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BlogNetworkEntity(
    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("rows")
    @Expose
    var rows: List<RowNetworkEntity>
)