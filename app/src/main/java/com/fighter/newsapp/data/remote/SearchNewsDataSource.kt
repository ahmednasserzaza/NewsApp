package com.fighter.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fighter.newsapp.data.local.ArticleDao
import com.fighter.newsapp.data.local.ArticleEntity
import com.fighter.newsapp.data.local.toArticleEntity
import com.fighter.newsapp.data.remote.utilities.handleApiResponse
import com.fighter.newsapp.domain.entity.ArticleType
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchNewsDataSource @Inject constructor(
    private val service: NewsService,
    private val articleDao: ArticleDao,
) : PagingSource<Int, ArticleEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleEntity>): Int? {
        return state.anchorPosition
    }

    private var newsSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String) {
        newsSearchText = searchText
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {
        val pageNumber = params.key ?: 1
        return try {
            val response = handleApiResponse(service.searchForNews(newsSearchText, pageNumber, 10))
            response.articles.forEach {
                if (articleDao.getArticleIdByTitle(it.title!!) == null) {
                    articleDao.insertArticle(
                        it.toArticleEntity().copy(articleType = ArticleType.SEARCH_NEWS.type)
                    )
                }
            }
            val pagedResponse = response.articles.map { it.toArticleEntity() }

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