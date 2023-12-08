package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import javax.inject.Inject

class GetEgyptNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getEgyptNews().sortedByDescending { articleDto ->
            articleDto.publishedAt
        }.map {
            if (articleHasBookmarked(it.title!!)) {
                it.toEntity().copy(isBookmarked = true)
            } else {
                it.toEntity().copy(isBookmarked = false)
            }
        }
    }

    private suspend fun articleHasBookmarked(title: String): Boolean {
        return newsRepository.isArticleBookmarked(title)
    }
}