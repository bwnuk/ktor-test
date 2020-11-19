package com.github.wnuk.entity

import java.util.*

data class ArticleDao(
    val id: Int,
    val title: String,
    val desc: String,
    val date: Date
)


data class ArticleListDto(
    val size: String,
    val start: String,
    val articles: List<ArticleDao?>
)

data class ArticleRequest(
    val title: String,
    val desc: String,
    val date: Date
)