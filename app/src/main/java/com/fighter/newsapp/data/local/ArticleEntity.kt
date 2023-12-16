package com.fighter.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.entity.ArticleType

@Entity("Article_TABLE")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val articleHeader: String,
    val articleDescription: String,
    val articleContent: String,
    val articleImageUrl: String,
    val articleDate: String,
    val isBookmarked: Boolean,
    val articleType: Int,
)

fun ArticleEntity.toEntity(): Article {
    return Article(
        id = id,
        author = "",
        title = articleHeader,
        description = articleDescription,
        content = articleContent,
        publishedAt = articleDate,
        imageUrl = articleImageUrl,
        url = "",
        isBookmarked = isBookmarked,
        articleType = ArticleType.getArticleType(articleType)
    )
}

fun ArticleDto.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleHeader = title ?: "",
        articleDescription = description ?: "",
        articleContent = content ?: "",
        articleImageUrl = urlToImage ?: "",
        articleDate = publishedAt,
        isBookmarked = false,
        articleType = 0
    )
}

fun List<ArticleEntity>.toEntity() = map { it.toEntity() }