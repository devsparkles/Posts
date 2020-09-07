package com.devsparkle.posts.core.di.modules


import com.devsparkle.posts.core.data.PostsDataSource
import com.devsparkle.posts.core.viewmodel.PostsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun providePostsViewModelFactory(dataSource: PostsDataSource): PostsViewModelFactory {
        return PostsViewModelFactory(dataSource)
    }
}