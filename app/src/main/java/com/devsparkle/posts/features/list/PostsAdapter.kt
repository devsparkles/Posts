package com.devsparkle.posts.features.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devsparkle.posts.R
import com.devsparkle.posts.core.data.models.Post
import com.devsparkle.posts.core.ui.util.AvatarLoader
import com.mikhaellopez.hfrecyclerviewkotlin.HFRecyclerView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_post.view.*


class PostsAdapter(private val baseAvatarUrl: String) : HFRecyclerView<Post>(true, false) {

    private val clickSubject = PublishSubject.create<Post>()
    val clickEvent: Observable<Post> = clickSubject

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class ItemViewHolder(
            private val view: View,
            private val baseAvatarUrl: String,
            private val clickObserver: Observer<Post>
        ) : ViewHolder(view),
            View.OnClickListener {
            private lateinit var post: Post

            init {
                view.setOnClickListener(this)
            }

            fun bind(post: Post) {
                this.post = post

                view.title.text = post.title
                view.body.text = post.body

                AvatarLoader.load(post.user.email, view.author_image, baseAvatarUrl)
            }

            override fun onClick(v: View?) {
                clickObserver.onNext(post)
            }
        }

        class HeaderViewHolder(view: View) : ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder.ItemViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getHeaderView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder? {
        return ViewHolder.HeaderViewHolder(
            inflater.inflate(
                R.layout.header_post_list,
                parent,
                false
            )
        )
    }

    override fun getFooterView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder? {
        return null
    }

    override fun getItemView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder.ItemViewHolder(
            inflater.inflate(R.layout.row_post, parent, false),
            baseAvatarUrl,
            clickSubject
        )
    }
}