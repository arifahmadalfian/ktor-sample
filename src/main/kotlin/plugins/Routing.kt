package com.arifahmadalfian.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get

fun Application.configureRouting() {
    install(RoutingRoot){
        route("/", HttpMethod.Get){
            handle {
                call.respondText("Hello world 123")
            }
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("blogs/{id}"){
            val id = call.pathParameters["id"]
            val q1 = call.queryParameters["q1"]
            val q2 = call.queryParameters["q2"]
            call.respondText("Blog with id: $id & query1 is $q1 , q2 is $q2")
        }

        typeSafeRoutes()
        dynamicRoutes()
        accountRoutes()
    }
}

fun Route.typeSafeRoutes(){
    get<Blogs>{ blogs ->
        val sort = blogs.sort
        call.respondText("Sort order : $sort")
    }

    delete<Blogs.Blog>{ blog ->
        val sort = blog.parent.sort
        val id = blog.id
        call.respondText("Blog id : $id sorting : $sort")
    }
}

fun Route.dynamicRoutes(){
    get(Regex(".+/test")){
        call.respondText("Test api response")
    }

    //api/v1/users
    //api/v2/users
    //api/v3/users

    get(Regex("api/(?<apiVersion>v[1-3])/users")){
        val version = call.pathParameters["apiVersion"]
        call.respondText("Api Version is $version")
    }
}

fun Route.accountRoutes(){

    // accounts/user/{id} creating
    // accounts/user/{id} deleting

    // accounts/auth/login
    // accounts/auth/signup

    route("accounts"){
        route("users"){
            get {  }
            get ("{id}"){  }
            post (""){  }
            patch("{id}"){  }
        }
        route("auth"){
            post("login") {  }
            post("signup") {  }
        }
    }
}
