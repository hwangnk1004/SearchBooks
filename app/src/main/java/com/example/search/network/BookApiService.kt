package com.example.search.network

import com.example.search.model.DetailBook
import com.example.search.model.SearchResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApiService {

    @GET("search/{query}/{page}")
    fun fetchSearchBooks(
        @Path("query") query: String,
        @Path("page") page: Int
    ): Call<SearchResponseData>

    @GET("books/{isbn13}")
    fun fetchDetailBook(
        @Path("isbn13") isbn13: String,
    ): Call<DetailBook>

}