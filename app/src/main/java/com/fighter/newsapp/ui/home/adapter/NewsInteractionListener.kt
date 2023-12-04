package com.fighter.newsapp.ui.home.adapter

import com.fighter.newsapp.ui.base.BaseInteractionListener
import com.fighter.newsapp.ui.mapper.ArticleUiState

interface NewsInteractionListener : BaseInteractionListener {
    fun onClickNewsItem(newsTitle: String)
    fun onClickBookMark(item: ArticleUiState)
}