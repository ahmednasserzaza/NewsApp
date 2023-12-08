package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.repository.NewsRepository
import javax.inject.Inject

class DoesArticleBookmarkedUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(title: String): Boolean {
        return newsRepository.isArticleBookmarked(title)
    }
}