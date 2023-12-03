package com.fighter.newsapp.data.remote.model

import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.utility.toDateTimeFormat

fun ArticleDto.toEntity(): Article {
    return Article(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        content = content,
        publishedAt = publishedAt.toDateTimeFormat()
    )
}