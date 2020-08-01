package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RowNetworkEntity(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("imageHref")
    @Expose
    val imageHref: String
) {
    override fun toString(): String {
        return "BlogRowResponse(title=$title, description='$description',imageHref='$imageHref')"
    }
}