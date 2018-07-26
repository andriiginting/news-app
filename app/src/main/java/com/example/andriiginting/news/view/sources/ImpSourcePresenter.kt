package com.example.andriiginting.news.view.sources

import android.content.Context
import android.util.Log
import android.util.TypedValue
import com.example.andriiginting.news.adapter.SourcesAdapter
import com.example.andriiginting.news.model.sources.Source
import com.example.andriiginting.news.network.NetworkClient
import com.example.andriiginting.news.network.NetworkRoutesInterface
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class ImpSourcePresenter(sourceView: SourceContract.View,
                         context: Context, adapter: SourcesAdapter) : SourceContract.Presenter {

    private var view: SourceContract.View? = null
    private var request = NetworkClient()
            .getRetrofitClient()
            .create(NetworkRoutesInterface::class.java)
    private var context: Context? = null
    private  var adapter: SourcesAdapter? = null

    init {
        view = sourceView
        this.context = context
        this.adapter = adapter
    }

    override fun attempToGetListOfSource(language: String, country: String,
                                         listOfSources: ArrayList<Source>) {
        ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMapSingle { ReactiveNetwork.checkInternetConnectivity() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isNetworkAvailable ->
                    if (isNetworkAvailable) {
                        getListOfSources(language, country, listOfSources)
                    } else {
                        RuntimeException("Not Accepted")
                    }
                }

    }

    override fun getListOfSources(language: String, country: String, listOfSources: ArrayList<Source>) {
        request.getAllSources(language, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    when {
                        response.isSuccessful -> {
                            for (result in response.body()!!.sources!!.iterator()) {
                                listOfSources.add(
                                        Source(result.sourcesId.toString(),
                                                result.sourcesName.toString(),
                                                result.sourcesDescription.toString(),
                                                result.sourceUrl.toString(),
                                                result.sourcesCategory.toString(),
                                                result.sourcesLanguage.toString(),
                                                result.sourcesCountry.toString()))
                            }
                            adapter?.notifyDataSetChanged()
                            Log.d("response-source", listOfSources.toString())
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 400 -> {
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 401 -> {

                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 429 -> {
                            view?.hideLoadingProgressBar()
                        }
                        response.code() == 500 -> {
                            view?.hideLoadingProgressBar()
                        }
                    }
                }, { error ->
                    try {
                        error.printStackTrace()
                    } catch (e: IOException) {
                        e.localizedMessage
                    }
                })

    }

    override fun convertDpToPx(dp: Int, context: Context): Int? {
        val r = context.resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

}