package com.fighter.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Article_TABLE")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val articleHeader: String,
    val articleDescription: String,
    val articleContent: String,
    val articleImageUrl: String,
)
