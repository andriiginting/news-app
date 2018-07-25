package com.example.andriiginting.news.model.article

class ArticleModel(val source: List<SourceArticleModel>,
                   val articleAuthor: String,
                   val articleTitle: String,
                   val articleDescription: String,
                   val articleUrl: String,
                   val articleImageUrl: String,
                   val articlePublished: String)