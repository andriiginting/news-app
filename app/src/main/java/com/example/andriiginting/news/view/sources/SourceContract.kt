package com.example.andriiginting.news.view.sources

import android.content.Context
import com.example.andriiginting.news.model.sources.Source
import com.example.andriiginting.news.model.sources.SourceResponse
import retrofit2.Response

interface SourceContract {
    interface View {
        fun showLoadingProgressBar()

        fun hideLoadingProgressBar()

        fun showErrorMessage(response :  Response<List<SourceResponse>>)

    }

    interface Presenter {
        fun attempToGetListOfSource(language: String, country: String,listOfSources: ArrayList<Source>)

        fun getListOfSources(language: String, country: String,listOfSources: ArrayList<Source>)

        fun convertDpToPx(dp: Int,context: Context): Int?
    }
}