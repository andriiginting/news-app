package com.example.andriiginting.news.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.andriiginting.news.R
import com.example.andriiginting.news.model.sources.Source
import com.example.andriiginting.news.view.article.ArticleActivity
import com.example.andriiginting.news.view.article.ArticleActivity.Companion.NEWS_DOMAIN
import com.example.andriiginting.news.view.article.ArticleActivity.Companion.NEWS_NAME

class SourcesAdapter(private val listOfSource: ArrayList<Source>)
    : RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_sources, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listOfSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listOfSource[position])
        holder.newsName.setOnClickListener { v ->
            val intent = Intent(v.context, ArticleActivity::class.java)
            intent.putExtra(NEWS_NAME,listOfSource[position].name)
            intent.putExtra(NEWS_DOMAIN,listOfSource[position].sourceUrl)
            v.context.startActivity(intent)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsName: TextView = itemView.findViewById(R.id.name_news_source)
        fun bindItems(data: Source) {
            newsName.text = data.name
        }


    }
}
