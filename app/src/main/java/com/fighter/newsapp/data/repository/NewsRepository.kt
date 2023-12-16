package com.fighter.newsapp.data.repository

import androidx.paging.Pager
import com.fighter.newsapp.data.local.ArticleEntity
import com.fighter.newsapp.data.remote.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getEgyptNews(): Flow<List<ArticleEntity>>

    suspend fun getLatestNews(): Flow<List<ArticleEntity>>

    suspend fun searchForNews(query: String): Pager<Int, ArticleEntity>

    suspend fun updateBookmarkArticle(id: Long)

    suspend fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

}