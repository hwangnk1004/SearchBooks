package com.example.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.model.Book
import com.example.search.model.BookUiModel
import com.example.search.repository.BookRepository
import com.example.search.repository.RemoteBookDataSource
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repo = BookRepository(RemoteBookDataSource())

    private val _bookListData = MutableLiveData<List<BookUiModel>>()
    val bookListData: LiveData<List<BookUiModel>> = _bookListData

    fun fetchSearchBooks(query: String) {
        viewModelScope.launch {
            val result: List<Book>? = repo.fetchSearchBooks()?.books

            val searchBookUiModelList = result?.map { data ->
                BookUiModel.newInstance(data)
            }.orEmpty()

            _bookListData.value = searchBookUiModelList
        }
    }

}