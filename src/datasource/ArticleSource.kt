package com.github.wnuk.datasource

import com.github.wnuk.entity.ArticleDao
import java.util.*

object ArticleSource {
    val articles = mutableListOf<ArticleDao>()

    fun isMade(): Boolean = articles.size > 0

    fun addArticle(
        title: String,
        desc: String,
        date: Date
    ): ArticleDao {
        val article = ArticleDao((articles.size + 1), title, desc, date)
        articles.add(article)
        return article
    }
}

