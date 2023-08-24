package com.example.posts.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.posts.api.RetrofitClient
import com.example.posts.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository {
    private val apiService = RetrofitClient.create()

    suspend fun getPosts(): LiveData<List<Post>> {
        val data = MutableLiveData<List<Post>>()

        apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Handle error here
            }
        })

        return data
    }
}
