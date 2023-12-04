package com.fighter.newsapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.fighter.newsapp.data.remote.model.toEntity
import com.fighter.newsapp.data.remote.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchNewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(searchQuery: String): Flow<PagingData<Article>> {
        return newsRepository.searchForNews(searchQuery).flow.map { pagingData ->
            pagingData.map { it.toEntity() }
        }
    }
}