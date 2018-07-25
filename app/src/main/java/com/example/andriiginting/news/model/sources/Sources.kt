package com.example.andriiginting.news.model.sources

import com.google.gson.annotations.SerializedName

class Sources {
    @SerializedName("id")
    var sourcesId: String? = null

    @SerializedName("name")
    var sourcesName: String? = null

    @SerializedName("description")
    var sourcesDescription: String? = null

    @SerializedName("url")
    var sourceUrl: String? = null

    @SerializedName("category")
    var sourcesCategory: String? = null

    @SerializedName("language")
    var sourcesLanguage: String? = null

    @SerializedName("country")
    var sourcesCountry: String? = null
}