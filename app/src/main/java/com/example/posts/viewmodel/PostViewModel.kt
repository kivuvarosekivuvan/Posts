package com.example.posts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.api.ApiInterface
import com.example.posts.model.Post
import com.example.posts.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    private val postRepository = PostRepository(apiInterface)

    val postLiveData = MutableLiveData<List<Post>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response: Response<List<Post>> = postRepository.getPosts()
                if (response.isSuccessful) {
                    val postList: List<Post> = response.body() ?: emptyList()
                    postLiveData.postValue(postList)
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (exception: Exception) {
                errorLiveData.postValue(exception.message)
            }
        }
    }
}
