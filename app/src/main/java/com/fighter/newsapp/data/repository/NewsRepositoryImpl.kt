package com.fighter.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fighter.newsapp.data.local.ArticleDao
import com.fighter.newsapp.data.local.ArticleEntity
import com.fighter.newsapp.data.local.toArticleEntity
import com.fighter.newsapp.data.remote.NewsService
import com.fighter.newsapp.data.remote.SearchNewsDataSource
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.data.remote.utilities.handleApiResponse
import com.fighter.newsapp.domain.entity.ArticleType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val searchNewsDataSource: SearchNewsDataSource,
    private val articleDao: ArticleDao,
) : NewsRepository {

    private val config =
        PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false)

    override suspend fun getEgyptNews(): Flow<List<ArticleEntity>> {
        val result = handleApiResponse(service.getEgyptNews())
        result.articles.forEach {
            if (articleDao.getArticleIdByTitle(it.title!!) == null) {
                articleDao.insertArticle(
                    it.toArticleEntity().copy(articleType = ArticleType.TOP_NEWS.type)
                )
            }
        }
        return articleDao.getTopArticles()
    }

    override suspend fun getLatestNews(): Flow<List<ArticleEntity>> {
        val result = handleApiResponse(service.getLatestNews())
        result.articles.forEach {
            if (articleDao.getArticleIdByTitle(it.title!!) == null) {
                articleDao.insertArticle(
                    it.toArticleEntity().copy(articleType = ArticleType.LATEST_NEWS.type)
                )
            }

        }
        return articleDao.getLatestArticles()
    }

    override suspend fun searchForNews(query: String): Pager<Int, ArticleEntity> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { searchNewsDataSource })
    }

    override suspend fun updateBookmarkArticle(id: Long) {
        return articleDao.updateBookmarkStatus(id)
    }

    override suspend fun getBookmarkedArticles(): Flow<List<ArticleEntity>> {
        return articleDao.getBookmarkedArticles()
    }
}