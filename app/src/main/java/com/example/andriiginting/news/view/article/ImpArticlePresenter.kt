package com.example.andriiginting.news.view.article

import android.content.Context
import android.util.Log
import com.example.andriiginting.news.adapter.ArticleAdapter
import com.example.andriiginting.news.model.article.ArticleModel
import com.example.andriiginting.news.model.article.SourceArticleModel
import com.example.andriiginting.news.network.NetworkClient
import com.example.andriiginting.news.network.NetworkRoutesInterface
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class ImpArticlePresenter(articleView: ArticleContract.View,
                          adapter: ArticleAdapter) : ArticleContract.Presenter {

    private var request = NetworkClient()
            .getRetrofitClient()
            .create(NetworkRoutesInterface::class.java)

    private var view: ArticleContract.View? = null
    private var adapter: ArticleAdapter? = null

    init {
        this.view = articleView
        this.adapter = adapter
    }

    override fun getListArticle(sourcesName: String, list: ArrayList<ArticleModel>) {
        request.getListOfArticle(sourcesName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    when {
                        response.isSuccessful -> {

                            for (result in response.body()?.articleData!!.iterator()) {
                                list.add(ArticleModel(result.articleAuthor.toString(),
                                        result.articletitle.toString(),
                                        result.articleDescription.toString(),
                                        result.articleUrl.toString(),
                                        result.articleImageUrl.toString(),
                                        result.articlePublishedAt.toString(),
                                        SourceArticleModel(result.articleSource?.sourceId.toString(),
                                                result.articleSource?.sourceName.toString())))
                            }
                            adapter?.notifyDataSetChanged()
                            view?.hideLoading()
                        }
                        response.code() == 400 -> {
                            view?.hideLoading()
                        }
                        response.code() == 401 -> {
                            view?.hideLoading()
                        }
                        response.code() == 429 -> {
                            view?.hideLoading()
                        }
                        response.code() == 500 -> {
                            view?.hideLoading()
                        }

                    }
                }
                        , { error ->
                    try {
                        error.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                })
    }

    override fun attemptGetListArticle(sourcesName: String, list: ArrayList<ArticleModel>, context: Context) {
        ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMapSingle { ReactiveNetwork.checkInternetConnectivity() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isNetworkAvailable ->
                    if (isNetworkAvailable) {
                        getListArticle(sourcesName, list)
                    } else {
                        //view?.hideLoadingProgressBar()
                        RuntimeException("Not Accepted")
                    }
                }
    }

}
