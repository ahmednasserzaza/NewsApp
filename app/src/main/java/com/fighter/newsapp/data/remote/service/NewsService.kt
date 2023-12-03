package com.fighter.newsapp.data.remote.service

import com.fighter.newsapp.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getEgyptNews(
        @Query("q") query: String = "Egypt",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortedBy") sortedBy: String = "popularity",
    ): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query("sources") sources: String = "bbc-news, the-next-web",
    ): Response<NewsResponse>


    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<NewsResponse>
}