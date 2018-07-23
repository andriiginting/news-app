package com.example.andriiginting.news.model.source

import com.google.gson.annotations.SerializedName

class Sources {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var sourceUrl: String? = null

    @SerializedName("category")
    var category: String? = null

    @SerializedName("language")
    var language: String? = null

    @SerializedName("country")
    var country: String? = null
}