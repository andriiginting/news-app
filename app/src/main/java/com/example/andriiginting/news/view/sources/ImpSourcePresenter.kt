package com.example.andriiginting.news.view.sources

import com.example.andriiginting.news.network.NetworkClient
import com.example.andriiginting.news.network.NetworkRoutesInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImpSourcePresenter(sourceView: SourceContract.View) : SourceContract.Presenter {

    private var view: SourceContract.View? = null
    private var request = NetworkClient()
            .getRetrofitClient()
            .create(NetworkRoutesInterface::class.java)

    init {
        view = sourceView
    }

    override fun getListOfSources(language: String, country: String) {
        request.getAllSources(language, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    when {
                        response.isSuccessful -> view?.hideLoadingProgressBar()
                        response.code() == 400 -> {
                            view?.showErrorMessage(response)
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 401 -> {
                            view?.showErrorMessage(response)
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 429 -> {
                            view?.showErrorMessage(response)
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 500 -> {
                            view?.showErrorMessage(response)
                            view?.hideLoadingProgressBar()
                        }
                    }
                }

    }
}