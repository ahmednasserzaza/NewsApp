package com.fighter.newsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.data.remote.service.NewsService
import com.fighter.newsapp.data.remote.utilities.handleApiResponse
import com.fighter.newsapp.domain.utility.BadRequestException
import com.fighter.newsapp.domain.utility.InternalServerException
import com.fighter.newsapp.domain.utility.NetworkException
import com.fighter.newsapp.domain.utility.NotFoundException
import com.fighter.newsapp.domain.utility.TooManyRequestsException
import com.fighter.newsapp.domain.utility.UnAuthorizedException
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val searchNewsDataSource: SearchNewsDataSource,
) : NewsRepository {

    private val config =
        PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false)

    override suspend fun getEgyptNews(): List<ArticleDto> {
        val result = handleApiResponse(service.getEgyptNews())
        return result.articles
    }

    override suspend fun getLatestNews(): List<ArticleDto> {
        val result = handleApiResponse(service.getLatestNews())
        return result.articles

    }

    override suspend fun searchForNews(query: String): Pager<Int, ArticleDto> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { searchNewsDataSource })
    }
}