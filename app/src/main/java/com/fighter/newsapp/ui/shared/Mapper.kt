package com.fighter.newsapp.ui.shared

import com.fighter.newsapp.domain.entity.Article

fun Article.toUiState(): ArticleUiState {
    return ArticleUiState(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
//        publishedAt = formatCreationDate(publishedAt),
        publishedAt = publishedAt,
        isBookMarked = isBookmarked
    )
}

fun List<Article>.toUiState() = map { it.toUiState() }

fun ArticleUiState.toEntity(): Article {
    return Article(
        author = "",
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
        publishedAt = publishedAt,
        isBookmarked = isBookMarked
    )
}