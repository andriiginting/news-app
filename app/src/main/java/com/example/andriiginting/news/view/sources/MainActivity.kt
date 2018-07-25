package com.example.andriiginting.news.view.sources

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.andriiginting.news.R
import com.example.andriiginting.news.adapter.SourcesAdapter
import com.example.andriiginting.news.model.sources.Source
import com.example.andriiginting.news.model.sources.SourceResponse
import com.example.andriiginting.news.network.Error.ErrorHandler
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.content_main.*

import retrofit2.Response

class MainActivity : AppCompatActivity(), SourceContract.View {


    private lateinit var  sourcePresenter: ImpSourcePresenter
    private lateinit var listOfSource: ArrayList<Source>
    private lateinit var dialog: BottomSheetDialog

    lateinit var adapter: SourcesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        listOfSource = ArrayList()
        adapter = SourcesAdapter(listOfSource)
        dialog = BottomSheetDialog(this)
        sourcePresenter = ImpSourcePresenter(this,applicationContext,adapter)

        sourcePresenter.attempToGetListOfSource("en","us",listOfSource)

        recycler_news_source.setHasFixedSize(true)
        recycler_news_source.layoutManager = LinearLayoutManager(this)
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
    override fun showBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun hideBottomSheet() {
        dialog.dismiss()
    }
}
