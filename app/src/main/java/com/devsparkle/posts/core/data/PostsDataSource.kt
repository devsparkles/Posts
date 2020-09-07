package com.devsparkle.posts.core.data

import com.devsparkle.posts.core.data.models.Comment
import com.devsparkle.posts.core.data.models.Post
import io.reactivex.Single

interface PostsDataSource {
    fun fetchPosts(): Single<List<Post>>
    fun fetchComments(postId: Long): Single<List<Comment>>
}