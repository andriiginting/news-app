package com.example.andriiginting.news.view.sources

import com.example.andriiginting.news.model.source.source.SourceResponse
import retrofit2.Response

interface SourceContract {
    interface View {
        fun showLoadingProgressBar()

        fun hideLoadingProgressBar()

        fun isNetworkConnected(): Boolean

        fun showErrorMessage(response :  Response<List<SourceResponse>>)

    }

    interface Presenter {
        fun getListOfSources(language: String, country: String)
    }
}