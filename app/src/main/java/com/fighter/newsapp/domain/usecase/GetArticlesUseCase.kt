package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.local.toEntity
import com.fighter.newsapp.data.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getAllArticles().map {
            it.sortedByDescending { articleDto ->
                articleDto.articleDate
            }.toEntity()
        }
    }
}