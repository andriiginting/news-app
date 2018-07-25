package com.example.andriiginting.news.model.article

import com.google.gson.annotations.SerializedName

class ArticleData {
    @SerializedName("source")
    var articleSource: SourceArticleResponse? = null

    @SerializedName("author")
    var articleAuthor: String? = null

    @SerializedName("title")
    var articletitle: String? = null

    @SerializedName("description")
    var articleDescription: String? = null

    @SerializedName("url")
    var articleUrl: String? = null

    @SerializedName("urlToImage")
    var articleImageUrl: String? = null

    @SerializedName("publishedAt")
    var articlePublishedAt: String? = null

}