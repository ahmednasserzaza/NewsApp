package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.local.toEntity
import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getAllArticles().map { articleList ->
            articleList.sortedByDescending { articleEntity ->
                articleEntity.articleDate
            }.map { articleEntity ->
                val isBookmarked = articleHasBookmarked(articleEntity.articleHeader)
                articleEntity.toEntity().copy(isBookmarked = isBookmarked)
            }
        }
    }

    private suspend fun articleHasBookmarked(title: String): Boolean {
        return newsRepository.isArticleBookmarked(title)
    }
}
