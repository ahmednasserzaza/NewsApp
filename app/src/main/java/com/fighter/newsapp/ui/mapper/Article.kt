package com.fighter.newsapp.ui.mapper

import com.fighter.newsapp.domain.entity.Article

data class ArticleUiState(
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
)

fun Article.toUiState(): ArticleUiState {
    return ArticleUiState(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
        publishedAt = publishedAt
    )
}