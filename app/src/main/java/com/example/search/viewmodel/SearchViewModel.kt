package com.example.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.model.BookUiModel
import com.example.search.repository.BookRepository
import com.example.search.repository.RemoteBookDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repo = BookRepository(RemoteBookDataSource())

    private val _bookListData = MutableLiveData<List<BookUiModel>>()
    val bookListData: LiveData<List<BookUiModel>> = _bookListData

    private var totalPage = 0

    private var beforeQuery: String = ""
    private var page = 1

    fun fetchSearchBooks(query: String) {
        viewModelScope.launch {
            if (beforeQuery != query) {
                beforeQuery = query
                page = 1
                _bookListData.value = emptyList()
            }
            handleFetchBooks(query)
        }
    }

    fun moreFetchSearchBooks(query: String) {
        viewModelScope.launch {
            if (totalPage == 0 || totalPage == page) return@launch
            page += 1
            handleFetchBooks(query)
        }
    }

    private fun handleFetchBooks(query: String) = viewModelScope.launch {

        val includeKeyword = query.split("-").first().split("|")
        val excludeKeyword = query.split("-").takeIf { it.size > 1 }?.lastOrNull()

        val searchBooks = includeKeyword.map {
            async {
                val result = repo.fetchSearchBooks(it, page)
                totalPage = result?.total ?: 0
                result?.books
            }
        }.awaitAll()
            .asSequence()
            .flatMap { it ?: emptyList() }
            .filterNot {
                !excludeKeyword.isNullOrBlank() && it.title!!.contains(
                    excludeKeyword,
                    ignoreCase = true
                )
            }.toList()

        val searchBookUiModelList = searchBooks.map {
            BookUiModel.newInstance(it)
        }

        val currentBookList = _bookListData.value
        _bookListData.value = currentBookList?.plus(searchBookUiModelList)
    }

}