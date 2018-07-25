package com.example.andriiginting.news.view.sources

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.andriiginting.news.R
import com.example.andriiginting.news.adapter.SourcesAdapter
import com.example.andriiginting.news.model.sources.Source
import com.example.andriiginting.news.model.sources.SourceResponse
import com.example.andriiginting.news.network.Error.ErrorHandler

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity(), SourceContract.View {

    private lateinit var  sourcePresenter: ImpSourcePresenter
    private lateinit var listOfSource: ArrayList<Source>

    lateinit var adapter: SourcesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        listOfSource = ArrayList()
        sourcePresenter = ImpSourcePresenter(this, listOfSource,applicationContext)
        adapter = SourcesAdapter(listOfSource)

        sourcePresenter.attempToGetListOfSource("en","us",listOfSource)

        val layoutManager = LinearLayoutManager(this)
        recycler_news_source.layoutManager = layoutManager
        recycler_news_source.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    override fun showLoadingProgressBar() {
        progressbar_news_sources.visibility = View.VISIBLE
    }

    override fun hideLoadingProgressBar() {
        progressbar_news_sources.visibility = View.GONE
    }

    override fun showErrorMessage(response: Response<List<SourceResponse>>) {
        val errorMessage = ErrorHandler.parseError(response).messageResponse()
        Toast.makeText(applicationContext,errorMessage,Toast.LENGTH_SHORT).show()
    }
}
