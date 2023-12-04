package com.fighter.newsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.data.remote.service.NewsService
import com.fighter.newsapp.domain.utility.InternalServerException
import com.fighter.newsapp.domain.utility.NotFoundException
import com.fighter.newsapp.domain.utility.UnAuthorizedException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val searchNewsDataSource: SearchNewsDataSource,
) : NewsRepository {

    private val config =
        PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false)

    override suspend fun getEgyptNews(): List<ArticleDto>? {
        val result = service.getEgyptNews()
        if (result.isSuccessful) {
            return result.body()?.articles?.sortedByDescending { it.publishedAt }
        } else {
            when (result.code()) {
                400 -> throw NotFoundException()
                401 -> throw UnAuthorizedException()
                500 -> throw InternalServerException()
                else -> {
                    throw Exception()
                }
            }
        }
    }

    override suspend fun getLatestNews(): List<ArticleDto>? {
        val result = service.getLatestNews()
        if (result.isSuccessful) {
            return result.body()?.articles?.sortedByDescending { it.publishedAt }
        } else {
            when (result.code()) {
                400 -> throw NotFoundException()
                401 -> throw UnAuthorizedException()
                500 -> throw InternalServerException()
                else -> {
                    throw Exception()
                }
            }
        }
    }

    override suspend fun searchForNews(query: String): Pager<Int, ArticleDto> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { searchNewsDataSource })
    }
}