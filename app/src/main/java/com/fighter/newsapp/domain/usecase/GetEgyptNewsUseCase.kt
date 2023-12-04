package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.remote.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article

class GetEgyptNewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getEgyptNews()?.map { it.toEntity() } ?: emptyList()
    }
}