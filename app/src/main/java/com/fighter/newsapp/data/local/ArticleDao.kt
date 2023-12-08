package com.fighter.newsapp.data.local

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

    @Query("DELETE FROM Article_TABLE WHERE articleHeader = :title")
    suspend fun deleteArticle(title: String)

    @Query("SELECT * FROM Article_TABLE")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT COUNT(*) FROM Article_TABLE WHERE articleHeader = :title")
    suspend fun doesArticleBookmarked(title: String): Boolean
}