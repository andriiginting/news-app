package com.example.andriiginting.news.model.article

data class ArticleModel(
                   val articleAuthor: String,
                   val articleTitle: String,
                   val articleDescription: String,
                   val articleUrl: String,
                   val articleImageUrl: String,
                   val articlePublished: String,
                   val source: SourceArticleModel)