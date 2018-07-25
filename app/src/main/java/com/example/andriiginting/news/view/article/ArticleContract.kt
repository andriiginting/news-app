package com.example.andriiginting.news.view.article

interface ArticleContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun setToolbarTitle(title: String?)
        fun setToolbarSubtitle(subtitle: String?)
    }

    interface Presenter{

    }
}