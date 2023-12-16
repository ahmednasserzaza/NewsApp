package com.fighter.newsapp.domain.usecase

import com.fighter.newsapp.data.repository.NewsRepository
import com.fighter.newsapp.domain.entity.Article
import javax.inject.Inject

class UpdateArticleBookmarkStatusUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.updateBookmarkArticle(article.id)
    }
}