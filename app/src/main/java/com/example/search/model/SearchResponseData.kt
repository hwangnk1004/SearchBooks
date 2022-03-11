package com.example.search.model

import com.google.gson.annotations.SerializedName

data class SearchResponseData(
    @SerializedName("error")
    val error: Int?,

    @SerializedName("total")
    val total: Int?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("books")
    val books: List<Book>?

)