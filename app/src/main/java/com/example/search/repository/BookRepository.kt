package com.example.search.repository

import com.example.search.model.DetailBook
import com.example.search.model.SearchResponseData
import com.example.search.source.BookDataSource

class BookRepository(private val source: BookDataSource) {

    suspend fun fetchSearchBooks(query: String, page: Int): SearchResponseData? {
        return source.fetchSearchBooks(query, page)
    }

    suspend fun fetchDetailBook(isbn13: String): DetailBook? {
        return source.fetchDetailBook(isbn13)
    }
}