package com.fighter.newsapp.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.data.remote.service.NewsService
import com.fighter.newsapp.data.remote.utilities.handleApiResponse
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchNewsDataSource @Inject constructor(
    private val service: NewsService,
) : PagingSource<Int, ArticleDto>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition
    }

    private var newsSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String) {
        newsSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = handleApiResponse(service.searchForNews(newsSearchText, pageNumber, 10))
            val pagedResponse = response.articles

            val nextPageNumber = pageNumber + 1

            LoadResult.Page(
                data = pagedResponse,
                prevKey = null,
                nextKey = if (pagedResponse.isEmpty()) null else nextPageNumber
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}