package com.devsparkle.posts.features.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devsparkle.posts.core.data.PostsDataSource
import com.devsparkle.posts.core.data.models.Comment
import com.devsparkle.posts.core.data.models.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalStateException

class PostDetailsViewModel(private val dataSource: PostsDataSource) : ViewModel() {
    val result = MutableLiveData<FetchResult>()
    val disposables = CompositeDisposable()
    var post: Post? = null

    fun fetchDetails() {
        post ?: throw IllegalStateException("No post set")

        disposables.add(dataSource.fetchComments(post!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                result.value = FetchResult.Fetching
            }.subscribe({ fetchResult ->
                result.value = FetchResult.Success(fetchResult)
            }, { error ->
                result.value = FetchResult.Error(error)
            })
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

    sealed class FetchResult {
        object Fetching : FetchResult()
        data class Success(val comments: List<Comment>) : FetchResult()
        data class Error(val exception: Throwable) : FetchResult()
    }
}