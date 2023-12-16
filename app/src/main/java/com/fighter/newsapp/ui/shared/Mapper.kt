package com.fighter.newsapp.ui.shared

import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.entity.ArticleType

fun Article.toUiState(): ArticleUiState {
    return ArticleUiState(
        id = id,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
        publishedAt = publishedAt,
        isBookMarked = isBookmarked,
        articleType = articleType.type
    )
}

fun List<Article>.toUiState() = map { it.toUiState() }

fun ArticleUiState.toEntity(): Article {
    return Article(
        id = id,
        author = "",
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
        publishedAt = publishedAt,
        isBookmarked = isBookMarked,
        articleType = ArticleType.getArticleType(articleType)
    )
}