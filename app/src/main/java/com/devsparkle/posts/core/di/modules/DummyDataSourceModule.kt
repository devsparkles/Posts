package com.devsparkle.posts.core.di.modules


import com.devsparkle.posts.core.data.PostsDataSource
import com.devsparkle.posts.core.data.jsonplaceholder.JpPostsDataSource
import com.devsparkle.posts.core.data.jsonplaceholder.JpPostsDummyService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DummyDataSourceModule {

    @Singleton
    @Provides
    fun providePostsDataSource(): PostsDataSource {
        return JpPostsDataSource(JpPostsDummyService())
    }
}