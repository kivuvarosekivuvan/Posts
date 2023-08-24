package com.example.posts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.model.Post
import com.example.posts.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val postRepository = PostRepository()

    val postLiveData = MutableLiveData<List<Post>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = postRepository.getPosts()
                if (response.isSuccessful) {
                    postLiveData.postValue(response.body())
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (exception: Exception) {
                errorLiveData.postValue(exception.message)
            }
        }
    }
}
