package com.example.andriiginting.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.andriiginting.news.R
import com.example.andriiginting.news.model.source.source.SourceModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class SourcesAdapter(private val listOfSource: ArrayList<SourceModel>)
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
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val newsImage: ImageView = itemView.findViewById(R.id.image_news_source)
        private val newsName: TextView = itemView.findViewById(R.id.name_news_source)

        fun bindItems(data: SourceModel) {
            newsName.text = data.source[adapterPosition].name
            setImageToHolder(data.source[adapterPosition].sourceUrl)
        }

        private fun setImageToHolder(url: String) {
            if (url.isEmpty()) {
                Picasso.get()
                        .load(R.drawable.ic_image_placeholder_black_24dp)
                        .error(R.color.design_error)
                        .fit()
                        .into(newsImage)
            } else {
                Picasso.get()
                        .load(url)
                        .error(R.color.design_error)
                        .fit()
                        .into(newsImage)
            }
        }
    }
}
