package com.fighter.newsapp.data.remote.model

import com.fighter.newsapp.domain.entity.Article

fun ArticleDto.toEntity(): Article {
    return Article(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = urlToImage ?: "",
        content = content ?: "",
        publishedAt = publishedAt ?: "",
        isBookmarked = false
    )
}