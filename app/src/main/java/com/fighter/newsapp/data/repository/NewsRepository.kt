package com.fighter.newsapp.data.repository

import androidx.paging.Pager
import com.fighter.newsapp.data.local.ArticleEntity
import com.fighter.newsapp.data.remote.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getEgyptNews(): List<ArticleDto>

    suspend fun getLatestNews(): List<ArticleDto>

    suspend fun searchForNews(query: String): Pager<Int, ArticleDto>

    suspend fun saveArticle(article: ArticleEntity)

    suspend fun deleteArticle(title:String)

    suspend fun getAllArticles(): Flow<List<ArticleEntity>>

    suspend fun isArticleBookmarked(title:String):Boolean
}