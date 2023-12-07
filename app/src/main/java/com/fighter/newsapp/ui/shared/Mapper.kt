package com.fighter.newsapp.ui.shared

import android.os.Build
import androidx.annotation.RequiresApi
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.ui.utilities.formatDateString

@RequiresApi(Build.VERSION_CODES.O)
fun Article.toUiState(): ArticleUiState {
    return ArticleUiState(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        content = content,
        publishedAt = publishedAt.formatDateString()
    )
}