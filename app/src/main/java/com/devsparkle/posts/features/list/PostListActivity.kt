package com.devsparkle.codes.posts.features.list

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devsparkle.posts.R
import com.devsparkle.posts.core.PostsApplication
import com.devsparkle.posts.core.data.models.Post
import com.devsparkle.posts.core.viewmodel.PostsViewModelFactory
import com.devsparkle.posts.features.list.PostListViewModel
import com.devsparkle.posts.features.list.PostsAdapter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: PostsViewModelFactory

    @Inject
    lateinit var postsAdapter: PostsAdapter

    private lateinit var postList: RecyclerView
    private lateinit var statusText: TextView

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        setContentView(R.layout.activity_post_list)

        PostsApplication.instance.component.inject(this)

        postList = findViewById<RecyclerView>(R.id.post_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PostListActivity)
            adapter = postsAdapter
        }

        disposables.add(postsAdapter.clickEvent.subscribe { post -> handlePostClick(post) })

        statusText = findViewById(R.id.status_text)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(PostListViewModel::class.java)
        viewModel.result.observe(
            this,
            Observer<PostListViewModel.FetchResult> { result -> processResult(result) })

        viewModel.fetchPosts()
    }

    private fun processResult(result: PostListViewModel.FetchResult) {
        when (result) {
            is PostListViewModel.FetchResult.Fetching -> {
                statusText.visibility = View.VISIBLE
                statusText.setText(R.string.fetching)
            }
            is PostListViewModel.FetchResult.Error -> {
                statusText.visibility = View.VISIBLE
                statusText.setText(R.string.error)
            }
            is PostListViewModel.FetchResult.Success -> {
                statusText.visibility = View.INVISIBLE
                postsAdapter.data = result.posts.shuffled() // Shuffle for cosmetic purposes
            }
        }
    }

    private fun handlePostClick(post: Post) {

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
