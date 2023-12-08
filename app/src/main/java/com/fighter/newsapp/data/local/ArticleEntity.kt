package com.fighter.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fighter.newsapp.domain.entity.Article

@Entity("Article_TABLE")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val articleHeader: String,
    val articleDescription: String,
    val articleContent: String,
    val articleImageUrl: String,
    val articleDate: String,
    val isBookmarked: Boolean,
)

fun ArticleEntity.toEntity(): Article {
    return Article(
        author = "",
        title = articleHeader,
        description = articleDescription,
        content = articleContent,
        publishedAt = articleDate,
        imageUrl = articleImageUrl,
        url = "",
        isBookmarked = isBookmarked
    )
}

fun List<ArticleEntity>.toEntity() = map { it.toEntity() }