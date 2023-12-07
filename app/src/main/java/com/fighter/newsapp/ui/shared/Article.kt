package com.fighter.newsapp.ui.shared

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUiState(
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val imageUrl: String = "",
    val publishedAt: String = "",
    val content: String = "",
): Parcelable
