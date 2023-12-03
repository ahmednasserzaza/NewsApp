package com.fighter.newsapp.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fighter.newsapp.data.remote.model.ArticleDto
import com.fighter.newsapp.data.remote.service.NewsService
import kotlin.properties.Delegates

class SearchNewsDataSource(private val service: NewsService) : PagingSource<Int, ArticleDto>() {
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
            val response = service.searchForNews(newsSearchText, pageNumber, 10)
            val pagedResponse = response.body()

            val nextPageNumber = pageNumber + 1

            LoadResult.Page(
                data = pagedResponse?.articles ?: emptyList(),
                prevKey = null,
                nextKey = if (pagedResponse?.articles?.isEmpty() == true) null else nextPageNumber
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}