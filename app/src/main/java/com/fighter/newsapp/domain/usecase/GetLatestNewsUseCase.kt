package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.remote.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article

class GetLatestNewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getLatestNews()?.map { it.toEntity() } ?: emptyList()
    }
}