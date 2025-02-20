package com.arifahmadalfian.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.Resources

fun Application.configureResources(){
    install(Resources)
}

@Resource("blogs")
class Blogs(val sort: String? = "new") {

    @Resource("{id}")
    data class Blog(val parent: Blogs = Blogs(), val id: String)
}