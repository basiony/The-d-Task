package com.example.thedtask.ItemClasses

import com.google.gson.annotations.SerializedName

data class ItemDetails(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<com.example.thedtask.ItemClasses.Data>
)