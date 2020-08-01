package com.task.repository.remote.blogs.models

import com.google.gson.annotations.SerializedName

data class RowNetworkEntity(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageHref") val imageHref: String
)