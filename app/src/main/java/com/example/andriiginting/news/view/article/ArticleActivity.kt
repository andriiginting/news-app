package com.example.andriiginting.news.view.article

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.example.andriiginting.news.R

import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.content_article.*

class ArticleActivity : AppCompatActivity(), ArticleContract.View {

    lateinit var newsName: String
    lateinit var newsDomain: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        newsName = intent.getStringExtra(NEWS_NAME)
        newsDomain = intent.getStringExtra(NEWS_DOMAIN)

        setToolbarTitle(newsName)
        setToolbarSubtitle(newsDomain)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
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

    companion object {
        const val NEWS_NAME = "newsName"
        const val NEWS_DOMAIN = "newsDomain"
    }
}
