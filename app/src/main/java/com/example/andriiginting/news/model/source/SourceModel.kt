package com.example.andriiginting.news.model.source

data class SourceModel(val status: String,
                       val source: List<Source>)

data class Source(val id: String,
                  val name: String,
                  val description: String,
                  val sourceUrl: String,
                  val category: String,
                  val language: String,
                  val country: String)
