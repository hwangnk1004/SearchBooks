package com.example.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.model.DetailBookUiModel
import com.example.search.repository.BookRepository
import com.example.search.repository.RemoteBookDataSource
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val repo = BookRepository(RemoteBookDataSource())

    private val _detailBook = MutableLiveData<DetailBookUiModel>()
    val detailBook: LiveData<DetailBookUiModel> = _detailBook

    fun fetchDetailBook(isbn13: String) {
        viewModelScope.launch {
            val result = repo.fetchDetailBook(isbn13)
            result?.let {
                _detailBook.value = DetailBookUiModel.newInstance(it)
            }
        }
    }
}