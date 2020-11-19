package com.github.wnuk.factory

import com.github.wnuk.datasource.ArticleSource
import java.util.*

object ArticleFactory {

    fun createMock() {
        var dateTemp = Date()
        for (i in 0..200 step 2) {
            mockArticle(
                i,
                "Piwko na skwerku",
                "Bylo piwo na skwerku - dluzszy opis. Data: $dateTemp Wersja: ",
                dateTemp
            )
            mockArticle(
                i + 1,
                "Piwko na skwerku",
                "Bylo piwo na skwerku - dluzszy opis. Data: $dateTemp Wersja: ",
                dateTemp
            )
        }
    }

    fun mockArticle(
        inc: Int,
        title: String,
        desc: String,
        date: Date
    ) =
        ArticleSource.addArticle(title + inc, desc + inc, date)
}