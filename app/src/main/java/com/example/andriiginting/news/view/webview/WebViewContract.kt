package com.example.andriiginting.news.view.webview

import android.content.Context

interface WebViewContract {
    interface View{
        fun showLoadingDialog()

        fun hideLoadingDialog()

        fun setToolbarTitle(title: String?)

        fun setToolbarSubtile(subtitle: String?)

        fun showWebView(url: String?)

        fun showBottomSheet()

        fun hideBottomSheet()
    }

    interface Presenter{
        fun loadWebView(url:String?,context: Context)
    }
}