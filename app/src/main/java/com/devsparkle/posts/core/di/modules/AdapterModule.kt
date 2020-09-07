package com.devsparkle.posts.core.di.modules

import com.devsparkle.posts.features.list.PostsAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AdapterModule {

    @Provides
    fun providePostsAdapter(@Named("base.avatar.url") baseAvatarUrl: String): PostsAdapter {
        return PostsAdapter(baseAvatarUrl)
    }


}