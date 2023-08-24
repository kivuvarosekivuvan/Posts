package com.example.posts.ui

import PostAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.ActivityMainBinding
import com.example.posts.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView: RecyclerView = binding.recyclerView
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        postAdapter = PostAdapter(emptyList())
        recyclerView.adapter = postAdapter

    }

    override fun onResume() {
        super.onResume()
        postViewModel.fetchPosts()
        postViewModel.postLiveData.observe(this, Observer { postList->
            postAdapter.updatePosts(postList)

        })

        postViewModel.errorLiveData.observe(this, Observer { error->
            Toast.makeText(
                baseContext,
                error ?: "An error occurred",
                Toast.LENGTH_LONG
            ).show()

        })




    }
}
