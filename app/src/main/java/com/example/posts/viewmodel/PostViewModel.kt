package com.example.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.repository.PostRepository
import com.example.posts.model.Post
import kotlinx.coroutines.launch


class PostViewModel : ViewModel() {
    val postsRepo=PostRepository()
    val postLiveData= MutableLiveData<List<Post>>()
    val errorLiveData= MutableLiveData<String>()

    fun fetchProducts(){
        viewModelScope.launch {
            val response = postsRepo.getPosts()
            if(response.isInitialized){
                postLiveData.postValue(response.body()?.posts)
            }else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


}


