package com.example.thedtask.ItemClasses

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("height")
    val height: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("width")
    val width: String
)