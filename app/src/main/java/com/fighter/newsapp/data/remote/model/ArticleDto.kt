package com.fighter.newsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String,
) {
    data class Source(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
    )
}

