package com.example.thedtask

import com.google.gson.annotations.SerializedName

data class ItemDetails(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<Data>
)