package com.example.andriiginting.news.model.sources

import com.google.gson.annotations.SerializedName

class SourceResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("sources")
    var sources: List<Sources>? = null

}