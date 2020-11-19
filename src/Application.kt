package com.github.wnuk

import com.github.wnuk.entity.ArticleRequest
import com.github.wnuk.exception.NotFoundException
import com.github.wnuk.services.ArticleServices
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/articles/all") {
            call.respond(ArticleServices.getAllArticles())
        }

        get("/articles/single/{id}") {
            val id = call.parameters["id"]
            val result = ArticleServices.getArticle(id?.toInt())
                ?: throw NotFoundException("Didnt find any article of this id: $id")
            call.respond(result)
        }

        get("/articles/chunk/{start}/{chunk}") {
            val start = call.parameters["start"]
            val chunk = call.parameters["chunk"]
            if (start != null && chunk != null) {
                val result = ArticleServices.getArticles(start.toInt(), chunk.toInt())
                call.respond(result)
            } else {
                throw NotFoundException("Wrong start $start and chunk $chunk")
            }
        }

        post("/articles/add") {
            val post = call.receive<ArticleRequest>()
            call.respond(ArticleServices.addArticle(post))
        }

        delete("/articles/delete/{id}") {
            val id = call.parameters["id"]
            var result = false
            if (id != null) {
                result = ArticleServices.deleteArticle(id.toInt())
            }

            if (result) {
                call.respond("OK")
            } else {
//				throw NotFoundException("Didnt find any article of this id: $id")
                call.respond("NOT FOUND")
            }
        }
    }
}

data class JsonSampleClass(val hello: String)

