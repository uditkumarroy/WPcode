package com.task.repository.remote.blogs.models

import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(
    @SerializedName("title") val title: String,
    @SerializedName("rows") val rowNetworkEntity: List<RowNetworkEntity>
)