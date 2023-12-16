package com.fighter.newsapp.ui.shared

import android.os.Parcelable
import com.fighter.newsapp.domain.entity.ArticleType
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUiState(
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val imageUrl: String = "",
    val publishedAt: String = "",
    val content: String = "",
    val isBookMarked: Boolean = false,
    val articleType: Int = ArticleType.TOP_NEWS.type,
) : Parcelable
