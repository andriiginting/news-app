package com.example.andriiginting.news.view.article

interface ArticleContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun setToolbarTitle()
        fun setToolbarSubtitle()
    }

    interface Presenter{

    }
}