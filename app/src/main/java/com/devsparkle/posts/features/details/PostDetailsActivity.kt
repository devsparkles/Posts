package com.devsparkle.posts.features.details

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devsparkle.posts.R
import com.devsparkle.posts.core.PostsApplication
import com.devsparkle.posts.core.data.models.Post
import com.devsparkle.posts.core.viewmodel.PostsViewModelFactory
import com.devsparkle.posts.features.details.PostDetailsViewModel.FetchResult
import javax.inject.Inject

const val POST_KEY = "POST_KEY"

class PostDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: PostsViewModelFactory

    @Inject
    lateinit var commentsAdapter: CommentsAdapter

    private lateinit var commentList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        setContentView(R.layout.activity_post_details)

        PostsApplication.instance.component.inject(this)

        val post: Post? = intent.extras?.getParcelable(POST_KEY) as Post?

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(PostDetailsViewModel::class.java)
        viewModel.result.observe(this, Observer<FetchResult> { result -> processResult(result) })
        viewModel.post = post

        commentsAdapter.post = post

        commentList = findViewById<RecyclerView>(R.id.comment_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PostDetailsActivity)
            adapter = commentsAdapter
        }

        viewModel.fetchDetails()
    }

    private fun processResult(result: FetchResult) {
        when (result) {
            is FetchResult.Fetching -> {
                commentsAdapter.status = getString(R.string.fetching)
            }
            is FetchResult.Error -> {
                commentsAdapter.status = getString(R.string.error)
            }
            is FetchResult.Success -> {
                if (result.comments.isEmpty()) {
                    commentsAdapter.status = getString(R.string.no_comments)
                } else {
                    commentsAdapter.status = ""
                }

                commentsAdapter.data = result.comments
            }
        }
    }
}