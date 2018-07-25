package com.example.andriiginting.news.model.article

import com.google.gson.annotations.SerializedName

class SourceArticleResponse {

    @SerializedName("id")
    var sourceId: String? = null

    @SerializedName("name")
    var sourceName: String? = null
}