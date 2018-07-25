package com.example.andriiginting.news.adapter

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.andriiginting.news.R
import com.example.andriiginting.news.model.article.ArticleModel
import com.example.andriiginting.news.view.webview.WebViewActivity
import com.example.andriiginting.news.view.webview.WebViewActivity.Companion.ARTICLE_NAME
import com.example.andriiginting.news.view.webview.WebViewActivity.Companion.ARTICLE_WEB_URL
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ArticleAdapter(listOfArticle: ArrayList<ArticleModel>)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(), Filterable {

    private var listArticle: ArrayList<ArticleModel>? = ArrayList()
    private var listArticleFilter: ArrayList<ArticleModel>? = ArrayList()

    init {
        this.listArticle = listOfArticle
        this.listArticleFilter = listArticle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)
        return ArticleAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listArticleFilter?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listArticleFilter?.get(position)?.let { holder.bindItemsData(it) }

        holder.card.setOnClickListener { v ->
            val intent = Intent(v.context, WebViewActivity::class.java)
            intent.putExtra(ARTICLE_WEB_URL, listArticleFilter?.get(position)?.articleUrl)
            intent.putExtra(ARTICLE_NAME, listArticleFilter?.get(position)?.articleTitle)
            v.context.startActivity(intent)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var newsImage: ImageView = itemView.findViewById(R.id.image_article)
        private var newsTitle: TextView = itemView.findViewById(R.id.title_news_article)
        private var loadingSpinner: ProgressBar = itemView.findViewById(R.id.progbar_item_article)
        var card: CardView = itemView.findViewById(R.id.card_article)

        fun bindItemsData(data: ArticleModel) {
            newsTitle.text = data.articleTitle
            setImageToHolder(data.articleImageUrl)
        }

        private fun setImageToHolder(imageUrl: String) {
            if (imageUrl.isEmpty()) {
                Picasso.get()
                        .load(R.drawable.ic_image_placeholder_black_24dp)
                        .error(R.drawable.ic_image_placeholder_black_24dp)
                        .fit()
                        .into(newsImage, object : Callback {
                            override fun onSuccess() {
                                loadingSpinner.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                loadingSpinner.visibility = View.GONE
                            }

                        })

            } else {
                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.ic_image_placeholder_black_24dp)
                        .fit()
                        .into(newsImage, object : Callback {
                            override fun onSuccess() {
                                loadingSpinner.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                loadingSpinner.visibility = View.GONE
                            }

                        })
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(char: CharSequence?): FilterResults {
                val charString = char.toString()

                if (charString.isEmpty()) {
                    listArticleFilter = this@ArticleAdapter.listArticle
                } else {
                    val filteredList = ArrayList<ArticleModel>()

                    for (dataArticle in this@ArticleAdapter.listArticle!!) {

                        if (dataArticle.articleTitle.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(dataArticle)
                        }
                    }

                    listArticleFilter = filteredList
                }

                val filterResult = FilterResults()
                filterResult.values = listArticleFilter

                return filterResult
            }

            override fun publishResults(char: CharSequence?, results: FilterResults?) {
                listArticleFilter = results?.values as ArrayList<ArticleModel>?
                notifyDataSetChanged()
            }

        }
    }

}