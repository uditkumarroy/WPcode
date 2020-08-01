package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("rows")
    @Expose
    val rows: List<RowNetworkEntity>
) {
    override fun toString(): String {
        return "BlogListSearchResponse(results=$rows, detail='$title')"
    }
}