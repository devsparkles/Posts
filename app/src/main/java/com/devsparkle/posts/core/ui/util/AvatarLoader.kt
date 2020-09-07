package com.devsparkle.posts.core.ui.util

import android.widget.ImageView
import com.devsparkle.posts.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation
import kotlinx.android.synthetic.main.row_post.view.*

class AvatarLoader {
    companion object {
        fun load(identifier: String?, imageView: ImageView, baseAvatarUrl: String) {
            val density = imageView.context.resources.displayMetrics.density

            Picasso.get()
                .load("$baseAvatarUrl$identifier.png")
                .placeholder(R.color.image_placeholder)
                .transform(GrayscaleTransformation())
                .transform(VignetteFilterTransformation(imageView.context))
                .transform(RoundedCornersTransformation((3 * density).toInt(), 0))
                .into(imageView.author_image)
        }
    }
}