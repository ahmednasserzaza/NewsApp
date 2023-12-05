package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.remote.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import javax.inject.Inject

class GetEgyptNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getEgyptNews().sortedByDescending { articleDto ->
            articleDto.publishedAt
        }.map { it.toEntity() }
    }
}