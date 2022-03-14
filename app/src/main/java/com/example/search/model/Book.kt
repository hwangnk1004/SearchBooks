package com.example.search.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("title")
    val title: String?,

    @SerializedName("subtitle")
    val subTitle: String?,

    @SerializedName("isbn13")
    val isbn13: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("url")
    val url: String?
)
