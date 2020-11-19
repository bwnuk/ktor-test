package com.github.wnuk.services

import com.github.wnuk.entity.ArticleDao
import com.github.wnuk.entity.ArticleListDto
import com.github.wnuk.entity.ArticleRequest
import com.github.wnuk.repositories.ArticleRepository

object ArticleServices {
    fun getAllArticles(): ArticleListDto {
        val articles = ArticleRepository.getAllArticles()
        return ArticleListDto(articles.size.toString(), "0", articles)
    }

    fun getArticles(start: Int, chunk: Int): ArticleListDto {
        val articles = ArticleRepository.getArticles(start, chunk)
        return ArticleListDto(articles.size.toString(), "0", articles)
    }

    fun getArticle(id: Int?): ArticleListDto? {
        return if (id != null) {
            val article: ArticleDao? = ArticleRepository.getArticle(id)
            if (article != null) {
                ArticleListDto("1", "0", listOf(article))
            } else {
                null
            }
        } else {
            null
        }
    }

    fun addArticle(articleRequest: ArticleRequest): ArticleListDto {
        val list = listOf(
            ArticleRepository.addArticle(
                articleRequest.title,
                articleRequest.desc,
                articleRequest.date
            )
        )
        return ArticleListDto(list.size.toString(), "0", list)
    }

    fun deleteArticle(id: Int): Boolean = ArticleRepository.deleteArticle(id)
}