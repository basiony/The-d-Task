package com.example.thedtask

import com.google.gson.annotations.SerializedName

//auto-generated data class for data object in the json api
data class Data(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Image,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("productDescription")
    var productDescription: String
)