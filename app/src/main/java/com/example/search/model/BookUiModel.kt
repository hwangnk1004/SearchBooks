package com.example.search.model

data class BookUiModel(
    val title: String,
    val subTitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
    val url: String
) {
    companion object {
        fun newInstance(data: Book): BookUiModel {
            return BookUiModel(
                title = data.title ?: "",
                subTitle = data.subTitle ?: "",
                isbn13 = data.isbn13 ?: "",
                price = data.price ?: "",
                image = data.image ?: "",
                url = data.url ?: ""
            )
        }
    }
}
