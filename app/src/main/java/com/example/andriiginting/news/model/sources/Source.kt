package com.example.andriiginting.news.model.sources

data class Source(val id: String,
                  val name: String,
                  val description: String,
                  val sourceUrl: String,
                  val category: String,
                  val language: String,
                  val country: String)