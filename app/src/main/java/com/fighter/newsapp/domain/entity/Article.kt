package com.fighter.newsapp.domain.entity

import com.fighter.newsapp.data.local.ArticleEntity

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isBookmarked: Boolean,
)

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleHeader = title,
        articleDescription = description,
        articleContent = content,
        articleDate = publishedAt,
        articleImageUrl = imageUrl,
        isBookmarked = isBookmarked
    )
}