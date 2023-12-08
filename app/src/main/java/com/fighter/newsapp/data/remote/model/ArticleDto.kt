package com.fighter.newsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("source") val sourceDto: SourceDto? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("urlToImage") val urlToImage: String? = null,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String? = null,
) {
    data class SourceDto(
        @SerializedName("id") val id: String? = null,
        @SerializedName("name") val name: String? = null,
    )
}

