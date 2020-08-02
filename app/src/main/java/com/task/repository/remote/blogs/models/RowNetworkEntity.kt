package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RowNetworkEntity(
    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("imageHref")
    @Expose
    var imageHref: String
)