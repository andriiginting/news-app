package com.example.andriiginting.news.view.webview

import android.content.Context
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImpWebViewPresenter(view: WebViewContract.View): WebViewContract.Presenter {
    private var view: WebViewContract.View? = null

    init {
        this.view = view
    }

    override fun loadWebView(url: String?,context: Context) {
        ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMapSingle { ReactiveNetwork.checkInternetConnectivity() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isNetworkAvailable ->
                    if (isNetworkAvailable) {
                            view?.showWebView(url)
                            view?.hideLoadingDialog()
                    } else {
                        view?.hideLoadingDialog()
                        RuntimeException("Not Accepted")
                    }
                }
    }
}