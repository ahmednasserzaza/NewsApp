package com.fighter.newsapp.data.remote.model

import android.annotation.SuppressLint
import com.fighter.newsapp.domain.entity.Article

@SuppressLint("NewApi")
fun ArticleDto.toEntity(): Article {
    return Article(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = urlToImage ?: "",
        content = content ?: "",
        publishedAt = publishedAt,
        isBookmarked = false
    )
}