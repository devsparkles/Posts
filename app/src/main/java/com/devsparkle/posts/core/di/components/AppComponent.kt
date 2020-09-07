package com.devsparkle.posts.core.di.components


import com.devsparkle.posts.core.di.modules.*
import com.devsparkle.posts.features.details.PostDetailsActivity
import com.devsparkle.posts.features.list.PostListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataSourceModule::class,
        ViewModelFactoryModule::class,
        AdapterModule::class,
        PropertyModule::class
    ]
)
interface AppComponent {
    fun inject(activity: PostListActivity)
    fun inject(activity: PostDetailsActivity)

}