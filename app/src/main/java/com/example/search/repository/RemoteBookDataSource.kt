package com.example.search.repository

import com.example.search.model.DetailBook
import com.example.search.model.SearchResponseData
import com.example.search.network.ApiUrls
import com.example.search.network.BookApiService
import com.example.search.source.BookDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class RemoteBookDataSource : BookDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiUrls.BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var bookApi: BookApiService = retrofit.create(BookApiService::class.java)

    override suspend fun fetchSearchBooks(query: String, page: Int): SearchResponseData? {
        return runCatching { bookApi.fetchSearchBooks(query, page).await() }.getOrNull()
    }

    override suspend fun fetchDetailBook(isbn13: String): DetailBook? {
        return runCatching { bookApi.fetchDetailBook(isbn13).await() }.getOrNull()
    }
}