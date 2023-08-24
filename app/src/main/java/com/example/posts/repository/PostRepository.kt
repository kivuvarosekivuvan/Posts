package com.example.posts.repository

import com.example.posts.api.ApiInterface
import com.example.posts.model.Post
import retrofit2.Call
import retrofit2.Response

class PostRepository(private val api: ApiInterface) {
    suspend fun getPosts(): Call<List<Post>> {
        return api.getPosts()
    }
}



