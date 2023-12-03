package com.fighter.newsapp.data.remote.repository

import androidx.paging.Pager
import com.fighter.newsapp.data.remote.model.ArticleDto

interface NewsRepository {

    suspend fun getEgyptNews(): List<ArticleDto>?

    suspend fun getLatestNews(): List<ArticleDto>?

    suspend fun searchForNews(query: String): Pager<Int, ArticleDto>
}