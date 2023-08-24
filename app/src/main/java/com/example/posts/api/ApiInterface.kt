package com.example.posts.api

import com.example.posts.model.Post
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("posts")
   suspend fun getPosts(): Call<List<Post>>

}
