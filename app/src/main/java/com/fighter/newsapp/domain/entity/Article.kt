package com.fighter.newsapp.domain.entity

import com.fighter.newsapp.data.local.ArticleEntity

data class Article(
    val id: Long,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isBookmarked: Boolean,
    val articleType: ArticleType,
)

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        articleHeader = title,
        articleDescription = description,
        articleContent = content,
        articleDate = publishedAt,
        articleImageUrl = imageUrl,
        isBookmarked = isBookmarked,
        articleType = articleType.type
    )
}

enum class ArticleType(val type: Int) {
    TOP_NEWS(0),
    LATEST_NEWS(1),
    SEARCH_NEWS(2);

    companion object {
        fun getArticleType(type: Int): ArticleType {
            entries.forEach {
                if (it.type == type) {
                    return it
                }
            }
            return TOP_NEWS
        }
    }
}