package com.devsparkle.posts.core.data.jsonplaceholder

import com.devsparkle.posts.core.data.jsonplaceholder.models.JpComment
import com.devsparkle.posts.core.data.jsonplaceholder.models.JpPost
import com.devsparkle.posts.core.data.jsonplaceholder.models.JpUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JpPostsService {
    @GET("posts")
    fun getPosts(): Single<List<JpPost>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Long): Single<List<JpComment>>

    @GET("users")
    fun getUsers(@Query("id") ids: List<Long>): Single<List<JpUser>>
}