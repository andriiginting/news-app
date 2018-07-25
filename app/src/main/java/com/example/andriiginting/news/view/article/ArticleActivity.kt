package com.example.andriiginting.news.view.article

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import com.example.andriiginting.news.R
import com.example.andriiginting.news.adapter.ArticleAdapter
import com.example.andriiginting.news.model.article.ArticleModel
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.content_article.*

class ArticleActivity : AppCompatActivity(), ArticleContract.View, SearchView.OnQueryTextListener {


    lateinit var newsName: String
    lateinit var newsDomain: String
    lateinit var newsId: String
    lateinit var dialog: BottomSheetDialog

    private lateinit var articlePresenter: ImpArticlePresenter
    private lateinit var adapter: ArticleAdapter
    private lateinit var listArticle: ArrayList<ArticleModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        newsName = intent.getStringExtra(NEWS_NAME)
        newsDomain = intent.getStringExtra(NEWS_DOMAIN)
        newsId = intent.getStringExtra(NEWS_ID)

        setToolbarTitle(newsName)
        setToolbarSubtitle(newsDomain)
        dialog = BottomSheetDialog(this)


        listArticle = ArrayList()
        adapter = ArticleAdapter(listOfArticle = listArticle)
        articlePresenter = ImpArticlePresenter(this,adapter)

        articlePresenter.attemptGetListArticle(newsId, listArticle,applicationContext)

        recycler_news_article.layoutManager = LinearLayoutManager(this)
        recycler_news_article.adapter = adapter
        recycler_news_article.setHasFixedSize(true)
        adapter.notifyDataSetChanged()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun showLoading() {
        progbar_news_article.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progbar_news_article.visibility = View.GONE
    }

    override fun setToolbarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun setToolbarSubtitle(subtitle: String?) {
        supportActionBar?.subtitle = subtitle
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        adapter.notifyDataSetChanged()
        return false
    }

    override fun showBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun hideBottomSheet() {
        dialog.dismiss()
    }

    companion object {
        const val NEWS_NAME = "newsName"
        const val NEWS_DOMAIN = "newsDomain"
        const val NEWS_ID = "newsId"
    }
}
