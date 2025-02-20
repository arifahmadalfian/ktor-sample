package com.arifahmadalfian.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("blogs/{id}") {
            val id = call.pathParameters["id"]
            val q1 = call.queryParameters["q1"]
            val q2 = call.queryParameters["q2"]
            call.respondText("Blog with id: $id q1: $q1 q2 : $q2")
        }
    }
}
