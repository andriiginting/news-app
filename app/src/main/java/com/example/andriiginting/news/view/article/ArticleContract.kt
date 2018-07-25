package com.example.andriiginting.news.view.article

import android.content.Context
import com.example.andriiginting.news.model.article.ArticleModel

interface ArticleContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun setToolbarTitle(title: String?)
        fun setToolbarSubtitle(subtitle: String?)

        fun showBottomSheet()

        fun hideBottomSheet()
    }

    interface Presenter{
        fun getListArticle(sourcesName: String,list: ArrayList<ArticleModel>)

        fun attemptGetListArticle(sourcesName: String,
                                  list: ArrayList<ArticleModel>,context: Context)

    }
}