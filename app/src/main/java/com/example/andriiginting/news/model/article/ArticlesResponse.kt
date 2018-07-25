package com.example.andriiginting.news.model.article

import com.google.gson.annotations.SerializedName

class ArticlesResponse {
    @SerializedName("status")
    var statusResponse: String? = null

    @SerializedName("totalResults")
    var resultArticle: Int? = null

    @SerializedName("articles")
    var articleData: List<ArticleData>? = null
}