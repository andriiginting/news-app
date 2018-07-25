package com.example.andriiginting.news.view.webview

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.andriiginting.news.R

import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.content_web_view.*

class WebViewActivity : AppCompatActivity(), WebViewContract.View {

    lateinit var webUrl: String
    lateinit var articleTitle: String
    private var progDialog: ProgressDialog? = null
    private lateinit var  webPresenter: ImpWebViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webPresenter = ImpWebViewPresenter(this)
        webUrl = intent.getStringExtra(ARTICLE_WEB_URL)
        articleTitle = intent.getStringExtra(ARTICLE_NAME)

        setToolbarTitle(articleTitle)
        setToolbarSubtile(webUrl)
        showLoadingDialog()
        webPresenter.loadWebView(webUrl,applicationContext)

    }

    override fun showLoadingDialog() {
        if (progDialog == null){
            progDialog = ProgressDialog(this)
            progDialog?.setMessage(getString(R.string.loading_indicator))
            progDialog?.isIndeterminate = true
        }
        progDialog?.show()

    }

    override fun hideLoadingDialog() {
        if (progDialog != null && progDialog!!.isShowing) {
            progDialog?.dismiss()
        }

    }

    override fun setToolbarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun setToolbarSubtile(subtitle: String?) {
        supportActionBar?.subtitle = subtitle
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun showWebView(url: String?) {
        webview_article.loadUrl(url)
        webview_article.settings.javaScriptEnabled = true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true

    }

    companion object {
        const val ARTICLE_WEB_URL = "webUrl"
        const val ARTICLE_NAME = "articleName"
    }

}
