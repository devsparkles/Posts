package com.devsparkle.posts.core.di.components


import com.devsparkle.codes.posts.features.list.PostListActivity
import com.devsparkle.posts.core.di.modules.*
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

}