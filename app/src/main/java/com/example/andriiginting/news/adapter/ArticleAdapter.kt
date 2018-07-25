package com.example.andriiginting.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.andriiginting.news.R
import com.example.andriiginting.news.model.article.ArticleModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ArticleAdapter(private val listOfArticle: ArrayList<ArticleModel>)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)
        return ArticleAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfArticle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItemsData(listOfArticle[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var newsImage: ImageView = itemView.findViewById(R.id.image_article)
        private var newsTitle: TextView = itemView.findViewById(R.id.title_news_article)
        private var loadingSpinner: ProgressBar = itemView.findViewById(R.id.progbar_item_article)

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
                                loadingSpinner.visibility = View.VISIBLE
                            }

                        })

            }else{
                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.ic_image_placeholder_black_24dp)
                        .fit()
                        .into(newsImage, object : Callback {
                            override fun onSuccess() {
                                loadingSpinner.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                loadingSpinner.visibility = View.VISIBLE
                            }

                        })
            }
        }
    }
}