package com.fighter.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Query("SELECT id FROM Article_TABLE WHERE articleHeader = :title LIMIT 1")
    suspend fun getArticleIdByTitle(title: String): Long?

    @Query("SELECT * FROM Article_TABLE WHERE articleType = 0")
    fun getTopArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM Article_TABLE WHERE articleType = 1")
    fun getLatestArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM Article_TABLE WHERE articleType = 2 AND articleHeader LIKE '%' || :searchQuery || '%'")
    fun getSearchArticles(searchQuery: String): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM Article_TABLE WHERE isBookmarked = 1")
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Query("UPDATE Article_TABLE SET isBookmarked = NOT isBookmarked WHERE id = :articleId")
    suspend fun updateBookmarkStatus(articleId: Long)
}