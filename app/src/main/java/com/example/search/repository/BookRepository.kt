package com.example.search.repository

import com.example.search.model.DetailBook
import com.example.search.model.SearchResponseData
import com.example.search.source.BookDataSource

class BookRepository(private val source: BookDataSource) {

    private var nextPage = 0

    suspend fun fetchSearchBooks(): SearchResponseData? {
        return source.fetchSearchBooks(nextPage++)
    }

    suspend fun fetchDetailBook(isbn13: String): DetailBook? {
        return source.fetchDetailBook(isbn13)
    }
}