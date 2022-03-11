package com.example.search.model

data class DetailBookUiModel(
    val error: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val language: String,
    val isbn10: String,
    val isbn13: String,
    val pages: String,
    val year: String,
    val rating: String,
    val description: String,
    val price: String,
    val image: String,
    val url: String
) {
    companion object {
        fun newInstance(data: DetailBook): DetailBookUiModel {
            return DetailBookUiModel(
                error = data.error ?: "",
                title = data.title ?: "",
                subtitle = data.subtitle ?: "",
                authors = data.authors ?: "",
                publisher = data.publisher ?: "",
                language = data.language ?: "",
                isbn10 = data.isbn10 ?: "",
                isbn13 = data.isbn13 ?: "",
                pages = data.pages ?: "",
                year = data.year ?: "",
                rating = data.rating ?: "",
                description = data.description ?: "",
                price = data.price ?: "",
                image = data.image ?: "",
                url = data.url ?: ""
            )
        }
    }
}