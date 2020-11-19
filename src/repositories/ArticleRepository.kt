package com.github.wnuk.repositories

import com.github.wnuk.datasource.ArticleSource
import com.github.wnuk.entity.ArticleDao
import com.github.wnuk.factory.ArticleFactory
import java.util.*

object ArticleRepository {
    fun getAllArticles(): List<ArticleDao> {
        isMade()
        return ArticleSource.articles
    }

    fun getArticles(start: Int, chunk: Int): List<ArticleDao> {
        isMade()
        return ArticleSource.articles.subList(start, start + chunk)
    }

    fun getArticle(id: Int): ArticleDao? {
        isMade()
        return if (id > ArticleSource.articles.size) {
            null
        } else {
            ArticleSource.articles[id]
        }
    }

    fun addArticle(
        title: String,
        desc: String,
        date: Date
    ): ArticleDao {
        isMade()
        return ArticleSource.addArticle(title, desc, date)
    }

    fun deleteArticle(id: Int): Boolean {
        isMade()
        if (ArticleSource.articles.size < id) {
            return false
        }
        ArticleSource.articles.removeAt(id)
        return true
    }

    private fun isMade() {
        if (!ArticleSource.isMade()) {
            ArticleFactory.createMock()
        }
    }
}