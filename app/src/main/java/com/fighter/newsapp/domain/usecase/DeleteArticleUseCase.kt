package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.entity.toArticleEntity
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article.toArticleEntity())
    }
}