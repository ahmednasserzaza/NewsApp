package com.fighter.newsapp.data.remote.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.fighter.newsapp.domain.entity.Article

@RequiresApi(Build.VERSION_CODES.O)
fun ArticleDto.toEntity(): Article {
    return Article(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = urlToImage ?: "",
        content = content ?: "",
        publishedAt = publishedAt ?: ""
    )
}