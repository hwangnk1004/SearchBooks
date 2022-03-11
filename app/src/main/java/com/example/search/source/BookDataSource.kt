package com.example.search.source

import com.example.search.model.DetailBook
import com.example.search.model.SearchResponseData

interface BookDataSource {
    suspend fun fetchSearchBooks(nextPage:Int): SearchResponseData?

    suspend fun fetchDetailBook(isbn13: String): DetailBook?
}